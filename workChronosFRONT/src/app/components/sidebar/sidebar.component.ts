import { Component } from '@angular/core';
import Cookies from 'js-cookie';

@Component({
  selector: 'sidebar-comp',
  imports: [],
  templateUrl: './sidebar.component.html',
})
export class SidebarComponent {
  logout() {
    Cookies.remove('userSession');
    window.location.href = '/login';
  }
}
