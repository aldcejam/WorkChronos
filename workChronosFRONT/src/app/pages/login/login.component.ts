import { Component, Inject } from '@angular/core';
import { AuthGateway } from '../../api/services/auth-gateway';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
})
export class LoginComponent {
  loginForm = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
  });

  constructor(
    private toastr: ToastrService,
    private authGateway: AuthGateway
  ) {}
 
  login() {
    const email = this.loginForm.value.email ?? '';
    const password = this.loginForm.value.password ?? '';
    this.authGateway.login({ email, password }).subscribe({
      next: (response) => {
        this.toastr.success('Login efetuado com sucesso!');
      },
      error: (error) => {
        this.toastr.error('Erro ao efetuar login!');
      },
    });
  }
}
