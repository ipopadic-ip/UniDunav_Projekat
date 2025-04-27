import { CanActivateFn, Router } from '@angular/router';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';

export function RoleGuard(expectedRoles: string[]): CanActivateFn {
  return (route, state) => {
    const authService = inject(AuthService); // <<< Samo inject

    const token = authService.getToken();
    if (!token || authService.isTokenExpired(token)) {
      authService.logout();
      return false;
    }

    const userRoles = authService.getRoles();
    const hasRole = expectedRoles.some(role => userRoles.includes(role));
    
    if (!hasRole) {
      authService.logout(); // ili navigacija na unauthorized
      return false;
    }

    return true;
  };
}
