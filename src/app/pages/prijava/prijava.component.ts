import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-prijava',
  imports: [CommonModule, FormsModule],
  templateUrl: './prijava.component.html',
  styleUrl: './prijava.component.css'
})
export class PrijavaComponent {
  email = '';
  password = '';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  onLogin() {
    this.authService.login(this.email, this.password).subscribe({
      next: () => {
        const roles = this.authService.getRoles();

        if (roles.includes('ADMIN')) {
          this.router.navigate(['/admin']);
        } else if (roles.includes('PROFESSOR')) {
          this.router.navigate(['/profesor']);
        } else if (roles.includes('STUDENT')) {
          this.router.navigate(['/student']);
        } else if (roles.includes('SLUZBENIK')) {
          this.router.navigate(['/sluzbenik']);
        } else {
          this.errorMessage = 'Nepoznata rola.';
        }
      },
      error: () => {
        this.errorMessage = 'Pogrešan email ili lozinka.';
      }
    });
  }
}
