import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';

@Component({
  selector: 'app-sluzbenik-dashboard',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './sluzbenik-dashboard.component.html',
  styleUrls: ['./sluzbenik-dashboard.component.css']
})
export class SluzbenikDashboardComponent {
  constructor(private authService: AuthService) {}

  logout(): void {
    this.authService.logout();
  }
}