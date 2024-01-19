//login.component.ts
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  login(): void {
    this.authService.login(this.email, this.password).subscribe(
      response => {
        console.log('Respuesta del servidor:', response);
        // Aquí puedes añadir lógica adicional si necesitas
        this.router.navigate(['/home']); // Navega a otra ruta tras el login exitoso
      },
      error => {
        console.error('Error al iniciar sesión', error);
        alert('Usuario o contraseña incorrectos'); // Manejo básico de errores
      }
    );
  }
}
