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
  userSession: LoginOutput | null;

  private getHeaders(): HttpHeaders {
    let headers = new HttpHeaders();
    if (this.userSession) {
      headers = headers.set('Authorization', `Bearer ${this.userSession.token}`);
    }
    return headers;
  }

  constructor(private http: HttpClient) {
    this.userSession = AuthGateway.getUserSession();
  }

  static getUserSession(): LoginOutput | null {
    const userSessionString = Cookies.get('userSession');
    return userSessionString ? JSON.parse(userSessionString) : null;
  }

  login({ email, password }: LoginInput): Observable<LoginOutput> {
    return this.http.post<LoginOutput>(this.API_CONFIG.ENDPOINTS.AUTH.LOGIN, {
      email,
      password,
    }).pipe(
      tap((response: LoginOutput) => {
        if (response.token) {
          Cookies.set('userSession', JSON.stringify(response), { expires: 7 });
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
  id: string;
  name: string;
  email: string;
  role: 'ADMIN' | 'USER';
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
