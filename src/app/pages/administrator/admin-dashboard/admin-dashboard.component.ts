import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../core/services/auth.service';
import { RouterModule } from '@angular/router';
// import { AdminPanelDugmeComponent } from '../../../components/administrator/admin-panel-dugme/admin-panel-dugme.component';

@Component({
  selector: 'app-admin-dashboard',
  imports: [CommonModule, RouterModule],
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent {
  constructor(private authService: AuthService) {}

  logout(): void {
    this.authService.logout();
  }

  dugmad = [
    { label: 'Moj Profil', route: '/admin/moj-profil' },
    { label: 'Upravljanje Postojećim korisnicima', route: '/admin/get-user' },
    { label: 'Upravljanje Ulogama', route: '/admin/uloge' },
    { label: 'Univerzitet', route: '/admin/univerzitet' },
    { label: 'Fakultet', route: '/admin/fakultet' },
    { label: 'Departman', route: '/admin/departman' },
    { label: 'Katedra', route: '/admin/katedra' },
    { label: 'Tip Studija', route: '/admin/tip-studija' },
    { label: 'Studijski Program', route: '/admin/studijski-program' },
    { label: 'Godina Studija', route: '/admin/godina-studija' },
    { label: 'Predmet', route: '/admin/predmet' },
    { label: 'Profesor predaje Predmet', route: '/admin/profesor-predmet' },
    { label: 'Dodaj Dokument', route: '/admin/dodaj-dokument' },
    // Dodaj dalje po potrebi...
  ];
}
