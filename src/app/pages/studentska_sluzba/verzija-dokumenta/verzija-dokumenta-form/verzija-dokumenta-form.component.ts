import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { VerzijaDokumentaService } from '../../../../core/services/verzija-dokumenta.service';
import { Router } from '@angular/router';
import { DokumentService } from '../../../../core/services/dokument.service';
import { Dokument } from '../../../../core/model/dokument.model';
import { UserService } from '../../../../core/services/user.service';
import { User } from '../../../../core/model/user.model';

@Component({
  selector: 'app-verzija-dokumenta-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './verzija-dokumenta-form.component.html',
})
export class VerzijaDokumentaFormComponent {
  private verzijaService = inject(VerzijaDokumentaService);
  private dokumentService = inject(DokumentService);
  private userService = inject(UserService);
  private router = inject(Router);

  dokumenti: Dokument[] = [];
  selectedDokumentId: number | null = null;
  sadrzaj = '';
  file?: File;
  email: string = '';

  ngOnInit() {
    this.dokumentService.getAllDokumentiAdmin().subscribe({
      next: (data) => (this.dokumenti = data.filter(d => !d.deleted)),
    });

    this.userService.getMe().subscribe({
      next: (user: User) => this.email = user.email,
      error: () => alert('Greška pri dohvatanju podataka o korisniku.'),
    });
  }

  onFileChange(event: any) {
    this.file = event.target.files[0];
  }

  sacuvajTekstualnuVerziju() {
    if (!this.selectedDokumentId || !this.sadrzaj || !this.email) {
      alert('Sva polja su obavezna!');
      return;
    }

    this.verzijaService.dodajTekstualnuVerziju(this.selectedDokumentId, {
      autor: this.email,
      sadrzaj: this.sadrzaj,
    }).subscribe({
      next: () => {
        alert('Tekstualna verzija uspešno dodata.');
        this.router.navigate(['/sluzbenik/verzije']);
      },
      error: () => alert('Greška pri dodavanju tekstualne verzije.'),
    });
  }

  uploadVerzija() {
    if (!this.selectedDokumentId || !this.file || !this.email) {
      alert('Morate izabrati fajl i biti prijavljeni.');
      return;
    }

    this.verzijaService.uploadVerzija(this.selectedDokumentId, this.file, this.email).subscribe({
      next: () => {
        alert('Fajl verzija uspešno uploadovana.');
        this.router.navigate(['/sluzbenik/verzije']);
      },
      error: () => alert('Greška pri uploadu fajla.'),
    });
  }

  otkazi() {
    this.router.navigate(['/sluzbenik/verzije']);
  }

  goBack() {
    this.router.navigate(['/sluzbenik/verzije']);
  }
}
