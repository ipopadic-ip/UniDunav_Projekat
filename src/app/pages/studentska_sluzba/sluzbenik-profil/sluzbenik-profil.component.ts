import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from '../../../core/services/user.service';
import { User } from '../../../core/model/user.model';
import { KorisnikProfilComponent } from '../../../components/shared/korisnik-profil/korisnik-profil.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sluzbenik-profil',
  standalone: true,
  imports: [CommonModule, KorisnikProfilComponent],
  templateUrl: './sluzbenik-profil.component.html',
})
export class SluzbenikProfilComponent implements OnInit {
  user?: User;
  private router = inject(Router);

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getMe().subscribe((data) => {
      this.user = data;
    });
  }

  goBack(): void {
    this.router.navigate(['/sluzbenik']);
  }
}
