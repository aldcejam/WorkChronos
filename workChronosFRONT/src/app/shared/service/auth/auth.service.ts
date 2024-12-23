import { UserGateway } from './../../../api/services/user-gateway';
import { Injectable } from '@angular/core';
import Cookies from 'js-cookie';
import { AuthGateway, LoginOutput } from '../../../api/services/auth-gateway';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private userGateway: UserGateway;

  constructor(userGateway: UserGateway) {
    this.userGateway = userGateway;
  }

  isLoggedIn(): boolean {
    const userSession = AuthGateway.getUserSession();
    return !!userSession;
  }

  setUserSession(userSession: LoginOutput): void {
    Cookies.set('userSession', 
      JSON.stringify(userSession), {
      secure: true,
      sameSite: 'Strict',
      expires: 1,
    });
  }

  clearuserSession(): void {
    Cookies.remove('userSession');
  }
}
