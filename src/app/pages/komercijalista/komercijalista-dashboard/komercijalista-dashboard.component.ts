import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-komercijalista-dashboard',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './komercijalista-dashboard.component.html',
  styleUrls: ['./komercijalista-dashboard.component.css']
})
export class KomercijalistaDashboardComponent {
  constructor(private authService: AuthService) {}

  logout(): void {
    this.authService.logout();
  }
}