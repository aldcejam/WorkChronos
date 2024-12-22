import { formatDateTime, formatDuration } from './../../shared/utils/formatDate';
import { API_CONFIG } from '../api.config';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { formatDate } from '../../shared/utils/formatDate';

@Injectable({
  providedIn: 'root'
})
export class AttendanceRecordGateway {
  
  private API_CONFIG = API_CONFIG;

  constructor(private http: HttpClient) {}

  // Função para formatar o retorno de todos os registros de presença
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

  // Método para obter o registro mais recente de presença
  getlatestByUserID(id: string): Observable<AttendanceRecordOutput> {
    return this.http.get<AttendanceRecordOutput>(this.API_CONFIG.ENDPOINTS.ATTENDANCE_RECORD.GET_LATEST_BY_USER_ID(id)).pipe(
      map(record => this.formatAttendanceRecordOutput(record)) // Formata o retorno
    );
  }

  // Método para listar todos os registros de presença por ID de usuário
  listByUserID(id: string): Observable<AttendanceRecordOutput[]> {
    return this.http.get<AttendanceRecordOutput[]>(this.API_CONFIG.ENDPOINTS.ATTENDANCE_RECORD.LIST_BY_USER_ID(id)).pipe(
      map(records => records.map(record => this.formatAttendanceRecordOutput(record))) // Formata todos os registros
    );
  }

  // Outros métodos...
  startDay(userID: string): Observable<AttendanceRecordOutput> {
    return this.http.post<AttendanceRecordOutput>(this.API_CONFIG.ENDPOINTS.ATTENDANCE_RECORD.START_DAY('userID'), {}).pipe(
      map(record => this.formatAttendanceRecordOutput(record))
    );
  }

  finishDay(userID: string): Observable<AttendanceRecordOutput> {
    return this.http.post<AttendanceRecordOutput>(this.API_CONFIG.ENDPOINTS.ATTENDANCE_RECORD.FINISH_DAY('userID'), {}).pipe(
      map(record => this.formatAttendanceRecordOutput(record))
    );
  }

  startBreak(userID: string): Observable<AttendanceRecordOutput> {
    return this.http.post<AttendanceRecordOutput>(this.API_CONFIG.ENDPOINTS.ATTENDANCE_RECORD.START_BREAK('userID'), {}).pipe(
      map(record => this.formatAttendanceRecordOutput(record))
    );
  }

  finishBreak(userID: string): Observable<AttendanceRecordOutput> {
    return this.http.post<AttendanceRecordOutput>(this.API_CONFIG.ENDPOINTS.ATTENDANCE_RECORD.FINISH_BREAK('userID'), {}).pipe(
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
