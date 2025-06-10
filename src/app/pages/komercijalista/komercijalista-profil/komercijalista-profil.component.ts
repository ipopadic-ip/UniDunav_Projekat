import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from '../../../core/services/user.service';
import { User } from '../../../core/model/user.model';
import { KorisnikProfilComponent } from '../../../components/shared/korisnik-profil/korisnik-profil.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-komercijalista-profil',
  standalone: true,
  imports: [CommonModule, KorisnikProfilComponent],
  templateUrl: './komercijalista-profil.component.html',
})
export class KomercijalistaProfilComponent implements OnInit {
  user?: User;
  private router = inject(Router);

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getMe().subscribe((data) => {
      this.user = data;
    });
  }

  goBack(): void {
    this.router.navigate(['/komercijalista']);
  }
}