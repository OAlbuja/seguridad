//header.component.ts
import { Component } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  constructor(public authService: AuthService, private router: Router) { }

  performLogout(): void {
    this.authService.logout();
    this.router.navigate(['/home']); // O cualquier ruta a la que quieras redirigir después del logout
  }

    navigateToLogin(): void {
    this.router.navigate(['/login']);
  }
}
