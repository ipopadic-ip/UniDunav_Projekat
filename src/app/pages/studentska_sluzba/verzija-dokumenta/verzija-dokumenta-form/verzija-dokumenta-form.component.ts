import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { VerzijaDokumentaService } from '../../../../core/services/verzija-dokumenta.service';
import { Router } from '@angular/router';
import { DokumentService } from '../../../../core/services/dokument.service';
import { Dokument } from '../../../../core/model/dokument.model';
import { UserService } from '../../../../core/services/user.service';
import { User } from '../../../../core/model/user.model';
import * as Diff2Html from 'diff2html';
import * as Diff from 'diff';

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
  dokumentZaUporediId: number | null = null;
  sadrzaj = '';
  file?: File;
  email: string = '';

  // za upoređivanje
  prikaziUporedi = false;
  verzije: any[] = [];
  izabraneVerzije: any[] = [];
  diffHtml = '';

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

  onDokumentChange(dokumentId: number) {
    this.selectedDokumentId = dokumentId;
    this.verzijaService.getPoslednjaVerzija(dokumentId).subscribe({
      next: verzija => {
        this.sadrzaj = verzija?.sadrzaj || '';
      },
      error: () => {
        this.sadrzaj = '';
        console.error('Greška pri učitavanju poslednje verzije.');
      }
    });
  }

  prikaziUporedjivanje() {
    this.prikaziUporedi = true;
    this.selectedDokumentId = null;
    this.verzije = [];
    this.izabraneVerzije = [];
    this.diffHtml = '';
  }

  ucitajVerzijeZaUporedi() {
  if (!this.dokumentZaUporediId) return;
  this.verzijaService.getVerzije(this.dokumentZaUporediId).subscribe({
    next: data => {
      this.verzije = data;
      this.izabraneVerzije = [];
      this.diffHtml = '';
    },
    error: () => alert('Greška pri učitavanju verzija.'),
  });
}

  toggleVerzija(v: any) {
    const index = this.izabraneVerzije.findIndex(x => x.id === v.id);
    if (index !== -1) {
      this.izabraneVerzije.splice(index, 1);
    } else if (this.izabraneVerzije.length < 2) {
      this.izabraneVerzije.push(v);
    }
  }

  uporedi() {
  if (this.izabraneVerzije.length !== 2) return;

  const [v1, v2] = this.izabraneVerzije;

  const rawDiff = Diff.createTwoFilesPatch(
    `Verzija ${v1.brojVerzije}`,
    `Verzija ${v2.brojVerzije}`,
    v1.sadrzaj || '',
    v2.sadrzaj || '',
    '', // optional header info
    '', // optional header info
    { context: 3 } // broj linija oko razlika (možeš menjati)
  );

  const diff = Diff2Html.html(Diff2Html.parse(rawDiff), {
    drawFileList: false,
    matching: 'lines',
    outputFormat: 'side-by-side',
  });

  this.diffHtml = diff;
}

  otkazi() {
    this.router.navigate(['/sluzbenik/verzije']);
  }

  goBack() {
    this.router.navigate(['/sluzbenik/verzije']);
  }
}

