import { API_CONFIG } from './../api.config';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AttendanceRecordGateway {
  
  private API_CONFIG = API_CONFIG;

  constructor(private http: HttpClient) {}

  getByUserID(id: string): Observable<AttendanceRecord> {
    return this.http.get<AttendanceRecord>(this.API_CONFIG.ENDPOINTS.ATTENDANCE_RECORD.GET_BY_USER_ID(id));
  }

  startDay(userID: string): Observable<AttendanceRecord> {
    return this.http.post<AttendanceRecord>(this.API_CONFIG.ENDPOINTS.ATTENDANCE_RECORD.START_DAY('userID'), {});
  }

  finishDay(userID: string): Observable<AttendanceRecord> {
    return this.http.post<AttendanceRecord>(this.API_CONFIG.ENDPOINTS.ATTENDANCE_RECORD.FINISH_DAY('userID'), {});
  }

  startBreak(userID: string): Observable<AttendanceRecord> {
    return this.http.post<AttendanceRecord>(this.API_CONFIG.ENDPOINTS.ATTENDANCE_RECORD.START_BREAK('userID'), {});
  }

  finishBreak(userID: string): Observable<AttendanceRecord> {
    return this.http.post<AttendanceRecord>(this.API_CONFIG.ENDPOINTS.ATTENDANCE_RECORD.FINISH_BREAK('userID'), {});
  }
}

interface AttendanceRecord {
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
  