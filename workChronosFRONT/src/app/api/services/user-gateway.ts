import { Injectable } from '@angular/core';
import { API_CONFIG } from '../api.config';
import { HttpClient } from '@angular/common/http';
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

  constructor(private http: HttpClient) {}

  create(user: UserInput): Observable<UserOutput> {
    return this.http
      .post<UserOutput>(this.API_CONFIG.ENDPOINTS.USERS.LIST, user)
      .pipe(map((user: UserOutput): UserOutput => this.formatUserOutput(user)));
  }

  getById(id: string): Observable<UserOutput> {
    return this.http
      .get<UserOutput>(this.API_CONFIG.ENDPOINTS.USERS.GET_BY_ID(id))
      .pipe(map((user: UserOutput): UserOutput => this.formatUserOutput(user)));
  }

  list(): Observable<UserOutput[]> {
    return this.http
      .get<UserOutput[]>(this.API_CONFIG.ENDPOINTS.USERS.LIST)
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

export interface UserInput {
  name: string;
  email: string;
  dailyWorkHours: string;
  password: string;
  role: 'ADMIN' | 'USER';
  phone: string;
  birthDate: string;
  startDate: string;
  endDate: string;
}
