import { Injectable } from '@angular/core';
import { API_CONFIG } from '../api.config';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import {
  formatDate,
  formatDateTime,
  formatDuration,
} from '../../shared/utils/formatDate';

@Injectable({
  providedIn: 'root',
})
export class UserGateway {
  private API_CONFIG = API_CONFIG;
  authToken: string | null;

  private formatUserOutput(user: UserOutput): UserOutput {
    return {
      id: user.id,
      name: user.name,
      email: user.email,
      dailyWorkHours: formatDuration(user.dailyWorkHours),
      password: user.password,
      role: user.role,
      phone: user.phone,
      birthDate: formatDate(user.birthDate),
      startDate: user.startDate,
      endDate: user.endDate,
      createdAt: formatDateTime(user.createdAt),
      updatedAt: formatDateTime(user.updatedAt),
    };
  }

  private getHeaders(): HttpHeaders {
    let headers = new HttpHeaders();
    if (this.authToken) {
      headers = headers.set('Authorization', `Bearer ${this.authToken}`);
    }
    return headers;
  }

  constructor(private http: HttpClient) {
    this.authToken = localStorage.getItem('authToken');
  }

  getById(id: string): Observable<UserOutput> {
    const headers = this.getHeaders();
    return this.http
      .get<UserOutput>(this.API_CONFIG.ENDPOINTS.USERS.GET_BY_ID(id), { headers })
      .pipe(map((user: UserOutput): UserOutput => this.formatUserOutput(user)));
  }

  list(): Observable<UserOutput[]> {
    const headers = this.getHeaders();
    return this.http
      .get<UserOutput[]>(this.API_CONFIG.ENDPOINTS.USERS.LIST, { headers })
      .pipe(
        map((users: UserOutput[]): UserOutput[] =>
          users.map((user) => this.formatUserOutput(user))
        )
      );
  }
}

export interface UserOutput {
  id: string;
  name: string;
  email: string;
  dailyWorkHours: string;
  password: string;
  role: 'ADMIN' | 'USER';
  phone: string;
  birthDate: string;
  startDate: string;
  endDate: string;
  createdAt: string;
  updatedAt: string;
}


