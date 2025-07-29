import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, MatIconModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private router: Router) {}

  onLogin() {
    // Simulação de login - em produção, implementar autenticação real
    if (this.email && this.password) {
      // Redirecionar para o dashboard após login
      this.router.navigate(['/dashboard']);
    } else {
      alert('Por favor, preencha todos os campos');
    }
  }
}

