import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from '../../components/shared/navbar/navbar.component';
  
@Component({
  selector: 'app-neregistrovani-korisnici',
  standalone: true,
  imports: [CommonModule, RouterModule, NavbarComponent],
  template: `
    <app-navbar [links]="navLinks"></app-navbar>
    <router-outlet></router-outlet>
  `
})
export class NeregistrovaniKorisniciComponent {
    navLinks = [
        { label: 'Početna', path: '/' },
        { label: 'Fakulteti', path: '/fakulteti' },
        { label: 'Prijava', path: '/prijava' }
      ];
}
