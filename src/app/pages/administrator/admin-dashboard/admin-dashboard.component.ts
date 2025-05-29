import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../core/services/auth.service';
import { RouterModule } from '@angular/router';
import { AdminPanelDugmeComponent } from '../../../components/administrator/admin-panel-dugme/admin-panel-dugme.component';

@Component({
  selector: 'app-admin-dashboard',
  imports: [CommonModule, RouterModule, AdminPanelDugmeComponent],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent {
  constructor(private authService: AuthService) {}

  logout(): void {
    this.authService.logout();
  }

  dugmad = [
    { label: 'Upravljanje Postojećim korisnicima', route: '/admin/korisnici' },
    { label: 'Dodaj novog korisnika', route: '/admin/add-user' },
    { label: 'Dodaj fakultet', route: '/admin/fakultet-novi' },
    { label: 'Obaveštenja', route: '/admin/obavestenja' },
    // Dodaj dalje po potrebi...
  ];
}
