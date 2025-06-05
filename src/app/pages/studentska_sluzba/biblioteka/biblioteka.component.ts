import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BibliotekaService } from '../../../core/services/biblioteka.service'; // prilagodi ako treba

@Component({
  selector: 'app-biblioteka',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './biblioteka.component.html',
  styleUrls: ['./biblioteka.component.css']
})
export class BibliotekaComponent {
  aktivnaKartica: 'izdavanje' | 'vracanje' = 'izdavanje';
  indeksInput = '';
  studentPronadjen = false;
  dostupneKnjige: any[] = [];
  iznajmljeneKnjige: any[] = [];
  loading = false;

  constructor(private bibliotekaService: BibliotekaService) {}

  pretraziZaIzdavanje() {
  if (!this.indeksInput) return;
  this.loading = true;

  this.bibliotekaService.getDostupneKnjigeZaStudenta(this.indeksInput).subscribe((knjige: any[]) => {
    this.dostupneKnjige = knjige;
    this.studentPronadjen = true;
    this.loading = false;
  }, () => this.loading = false);
}

 iznajmi(primerakId: number) {
  this.bibliotekaService.izdajKnjigu(primerakId, this.indeksInput).subscribe(() => {
    const knjiga = this.dostupneKnjige.find(k => k.primerakId === primerakId);
    if (knjiga) {
      knjiga.vecIznajmljena = true;
      knjiga.dostupna = false;
    }
  });
}

  pretraziZaVracanje() {
    if (!this.indeksInput) return;
    this.loading = true;

    this.bibliotekaService.getIznajmljivanjaStudenta(this.indeksInput).subscribe(res => {
      this.iznajmljeneKnjige = res;
      this.studentPronadjen = true;
      this.loading = false;
    }, () => this.loading = false);
  }

  vrati(knjigaId: number) {
    this.bibliotekaService.vratiKnjigu(knjigaId).subscribe(() => {
      this.iznajmljeneKnjige = this.iznajmljeneKnjige.filter(k => k.id !== knjigaId);
    });
  }

  reset() {
    this.indeksInput = '';
    this.studentPronadjen = false;
    this.dostupneKnjige = [];
    this.iznajmljeneKnjige = [];
  }
}