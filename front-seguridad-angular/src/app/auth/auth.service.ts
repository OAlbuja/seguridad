//auth.service.ts
import { Injectable, Inject, PLATFORM_ID } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { LoginResponse } from './login-response';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authUrl = 'http://localhost:8080/login';

  constructor(@Inject(PLATFORM_ID) private platformId: Object, private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post<LoginResponse>(this.authUrl, { email, password }).pipe(
      tap(response => {
        if (response && response.token) {
          localStorage.setItem('authToken', response.token);
        } else {
          // Manejar el caso en que el token no esté presente
          console.error('No se recibió el token en la respuesta', response);
        }
      }),
      catchError(this.handleError)
    );
  }

  isLoggedIn(): boolean {
    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('authToken');
      return !!token;
    }
    return false;
  }

  logout(): void {
    localStorage.removeItem('authToken');
  }

  getToken(): string | null {
    return localStorage.getItem('authToken');
  }

  private handleError(error: any) {
    return throwError(error);
  }
}
