import { formatDateTime, formatDuration } from './../../shared/utils/formatDate';
import { API_CONFIG } from '../api.config';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { formatDate } from '../../shared/utils/formatDate';
import { AuthGateway, LoginOutput } from './auth-gateway';

@Injectable({
  providedIn: 'root'
})
export class AttendanceRecordGateway {
  userSession: LoginOutput | null;
  private API_CONFIG = API_CONFIG;

  private getHeaders(): HttpHeaders {
      let headers = new HttpHeaders();
      this.userSession = AuthGateway.getUserSession();
    if (this.userSession) {
      headers = headers.set('Authorization', `Bearer ${this.userSession.token}`);
    }
    return headers;
  }

  constructor(private http: HttpClient) {
    this.userSession = AuthGateway.getUserSession();
  }

  private formatAttendanceRecordOutput(record: AttendanceRecordOutput): AttendanceRecordOutput {
    return {
      id: record.id,
      userId: record.userId,
      workDate: formatDate(record.workDate),
      workDuration: formatDuration(record.workDuration),
      entrie: {
        workStart: formatDateTime(record.entrie.workStart),
        workEnd: record.entrie.workEnd ? formatDateTime(record.entrie.workEnd) : record.entrie.workEnd,
        breaks: record.entrie.breaks?.map(breakItem => ({
          start: formatDateTime(breakItem.start),
          end: breakItem.end ? formatDateTime(breakItem.end) : breakItem.end,
          description: breakItem.description
        }))
      },
      createdAt: formatDateTime(record.createdAt),
      updatedAt: formatDateTime(record.updatedAt),
    };
  }

  getlatestByUserID(id: string): Observable<AttendanceRecordOutput> {
    const headers = this.getHeaders();
    return this.http.get<AttendanceRecordOutput>(this.API_CONFIG.ENDPOINTS.ATTENDANCE_RECORD.GET_LATEST_BY_USER_ID(id), {headers}).pipe(
      map(record => this.formatAttendanceRecordOutput(record)) // Formata o retorno
    );
  }

  listByUserID(id: string): Observable<AttendanceRecordOutput[]> {
    const headers = this.getHeaders();
    return this.http.get<AttendanceRecordOutput[]>(this.API_CONFIG.ENDPOINTS.ATTENDANCE_RECORD.LIST_BY_USER_ID(id), {headers}).pipe(
      map(records => records.map(record => this.formatAttendanceRecordOutput(record))) // Formata todos os registros
    );
  }

  startDay(userID: string): Observable<AttendanceRecordOutput> {
    const headers = this.getHeaders();
    return this.http.post<AttendanceRecordOutput>(this.API_CONFIG.ENDPOINTS.ATTENDANCE_RECORD.START_DAY(userID), {}, 
    {headers}
  ).pipe(
      map(record => this.formatAttendanceRecordOutput(record))
    );
  }

  finishDay(userID: string): Observable<AttendanceRecordOutput> {
    const headers = this.getHeaders();
    return this.http.post<AttendanceRecordOutput>(this.API_CONFIG.ENDPOINTS.ATTENDANCE_RECORD.FINISH_DAY(userID), {}, 
    {headers}
  ).pipe(
      map(record => this.formatAttendanceRecordOutput(record))
    );
  }

  startBreak(userID: string): Observable<AttendanceRecordOutput> {
    const headers = this.getHeaders();
    return this.http.post<AttendanceRecordOutput>(this.API_CONFIG.ENDPOINTS.ATTENDANCE_RECORD.START_BREAK(userID), {}, 
    {headers}
  ).pipe(
      map(record => this.formatAttendanceRecordOutput(record))
    );
  }

  finishBreak(userID: string): Observable<AttendanceRecordOutput> {
    const headers = this.getHeaders();
    return this.http.post<AttendanceRecordOutput>(this.API_CONFIG.ENDPOINTS.ATTENDANCE_RECORD.FINISH_BREAK(userID), {},
    {headers}
  ).pipe(
      map(record => this.formatAttendanceRecordOutput(record))
    );
  }
}

export interface AttendanceRecordOutput {
    id: string;
    userId: string;
    workDate: string;
    entrie: {
      workStart: string;
      workEnd: string | null;
      breaks?: {
        start: string;
        end: string | null;
        description: string | null;
      }[];
    };
    createdAt: string;
    updatedAt: string;
    workDuration: string;
}
