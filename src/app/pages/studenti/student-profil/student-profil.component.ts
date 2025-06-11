import { Component, OnInit, inject } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { User } from '../../../core/model/user.model';
import { UserService } from '../../../core/services/user.service';
import { KorisnikProfilComponent } from '../../../components/shared/korisnik-profil/korisnik-profil.component';

@Component({
  selector: 'app-student-profil',
  imports: [KorisnikProfilComponent, CommonModule],
  templateUrl: './student-profil.component.html',
  styleUrl: './student-profil.component.css'
})
export class StudentProfilComponent implements OnInit {
  user?: User;
  private userService = inject(UserService);
  private router = inject(Router);

  ngOnInit(): void {
    this.userService.getMe().subscribe((data) => {
      this.user = data;
    });
  }

   goBack(): void {
    this.router.navigate(['/student']);
  }
}
