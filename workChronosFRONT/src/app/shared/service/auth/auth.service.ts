import { Injectable } from '@angular/core';
import Cookies from 'js-cookie';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isLoggedIn(): boolean {
    const token = Cookies.get('authToken');
    return !!token;
  }

  setAuthToken(token: string): void {
    Cookies.set('authToken', token, {
      secure: true,
      sameSite: 'Strict',
      expires: 1,
    });
  }

  clearAuthToken(): void {
    Cookies.remove('authToken');
  }
}
