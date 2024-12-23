import { Injectable } from '@angular/core';
import { API_CONFIG } from '../api.config';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import {
  formatDate,
  formatDateTime,
  formatDuration,
} from '../../shared/utils/formatDate';
import Cookies from 'js-cookie';
import { AuthGateway, LoginOutput } from './auth-gateway';

@Injectable({
  providedIn: 'root',
})
export class UserGateway {
  private API_CONFIG = API_CONFIG;
  userSession: LoginOutput | null;

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
    this.userSession = AuthGateway.getUserSession();
    if (this.userSession) headers = headers.set('Authorization', `Bearer ${this.userSession.token}`);

    return headers;
  }

  constructor(private http: HttpClient) {
    this.userSession = AuthGateway.getUserSession();
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


