import { Injectable } from '@angular/core';
import { API_CONFIG } from '../api.config';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import Cookies from 'js-cookie';

@Injectable({
  providedIn: 'root',
})
export class AuthGateway {
  private API_CONFIG = API_CONFIG;
  authToken: string | null;

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

  login({ email, password }: LoginInput): Observable<LoginOutput> {
    return this.http.post<LoginOutput>(this.API_CONFIG.ENDPOINTS.AUTH.LOGIN, {
      email,
      password,
    }).pipe(
      tap((response: LoginOutput) => {
        if (response.token) {
          Cookies.set('authToken', response.token, { expires: 7 });
          window.location.href = '/';
        }
      })
    );
  }

  register(user: UserInput) {
    const headers = this.getHeaders();
    return this.http.post(this.API_CONFIG.ENDPOINTS.AUTH.REGISTER, user, {
      headers,
    });
  }
}

export interface LoginInput {
  email: string;
  password: string;
}

export interface LoginOutput {
  token: string;
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
