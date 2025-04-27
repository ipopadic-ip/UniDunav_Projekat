// src/app/core/guards/auth.guard.ts
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    const token = this.authService.getToken();

    if (token && !this.authService.isTokenExpired(token)) {
      return true;
    } else {
      this.authService.logout(); // izloguj ako nema token ili je istekao
      return false;
    }
    // if (this.authService.getToken()) {
    //   return true;
    // } else {
    //   this.router.navigate(['/prijava']);
    //   return false;
    // }
  }
}
