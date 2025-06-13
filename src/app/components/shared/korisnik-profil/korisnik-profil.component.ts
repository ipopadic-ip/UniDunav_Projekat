import { Component, inject, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../../core/services/user.service';
import { User } from '../../../core/model/user.model';

@Component({
  selector: 'app-korisnik-profil',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './korisnik-profil.component.html',
})
export class KorisnikProfilComponent implements OnInit {
  private userService = inject(UserService);

  staraSifra: string = '';
  novaSifra: string = '';

  @Input() readonly: boolean = false;


  @Input() user?: User;

  ngOnInit(): void {
    if (!this.user) {
      this.userService.getMe().subscribe((data) => {
        this.user = data;
      });
    }
  }

  sacuvaj() {
    // if (!this.user) return;
    if (this.readonly || !this.user) return;

    const dto = {
      ime: this.user.ime,
      prezime: this.user.prezime,
    };

    this.userService.updateMyProfile(dto).subscribe({
    next: () => alert('Uspešno sačuvano.'),
    error: () => alert('Greška pri čuvanju.'),
  });
  }

  promeniLozinku() {
  if (!this.staraSifra || !this.novaSifra) {
    alert('Molimo unesite obe lozinke.');
    return;
  }

  this.userService.changePassword(this.staraSifra, this.novaSifra).subscribe({
    next: () => {
      alert('Lozinka uspešno promenjena.');
      this.staraSifra = '';
      this.novaSifra = '';
    },
    error: () => {
      alert('Greška pri promeni lozinke. Proverite staru lozinku.');
    },
  });
}

}
