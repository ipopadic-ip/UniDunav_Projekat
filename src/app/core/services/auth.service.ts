import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { StorageService } from './storage.service'; // dodato ovo

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth/login';
  private tokenKey = 'authToken';
  private rolesKey = 'userRoles';

  isLoggedIn$ = new BehaviorSubject<boolean>(false); // inicijalno false

  constructor(
    private http: HttpClient,
    private router: Router,
    private storageService: StorageService // sada je sigurno ubačen
  ) {
    this.isLoggedIn$.next(this.hasToken()); // sada možeš pozvati
  }

  login(email: string, password: string): Observable<any> {
    return this.http.post<{ token: string }>(this.apiUrl, { email, password }).pipe(
      tap(response => {
        const token = response.token;
        this.storeToken(token);
        this.isLoggedIn$.next(true);
      })
    );
  }

  logout(): void {
    this.storageService.removeItem(this.tokenKey);
    this.storageService.removeItem(this.rolesKey);
    this.isLoggedIn$.next(false);
    this.router.navigate(['/prijava']);
  }

  private storeToken(token: string): void {
    this.storageService.setItem(this.tokenKey, token);
    const payload = JSON.parse(atob(token.split('.')[1]));
    this.storageService.setItem(this.rolesKey, JSON.stringify(payload.roles));
  }

  getToken(): string | null {
    return this.storageService.getItem(this.tokenKey);
  }

  getRoles(): string[] {
    const roles = this.storageService.getItem(this.rolesKey);
    return roles ? JSON.parse(roles) : [];
  }

  hasToken(): boolean {
    return !!this.storageService.getItem(this.tokenKey);
  }
}
