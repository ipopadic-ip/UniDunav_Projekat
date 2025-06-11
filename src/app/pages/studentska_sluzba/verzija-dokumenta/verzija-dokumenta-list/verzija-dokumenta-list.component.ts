// verzija-dokumenta-list.component.ts
import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VerzijaDokumentaService } from '../../../../core/services/verzija-dokumenta.service';
import { VerzijaDokumenta } from '../../../../core/model/verzija-dokumenta.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-verzija-dokumenta-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './verzija-dokumenta-list.component.html',
})
export class VerzijaDokumentaListComponent implements OnInit {
  private verzijaService = inject(VerzijaDokumentaService);
  private router = inject(Router);

  verzije: VerzijaDokumenta[] = [];

  ngOnInit(): void {
    this.ucitajVerzije();
  }

  ucitajVerzije() {
    // Ovo podrazumeva da ti backend vraća sve verzije za sve dokumente (ili ti unapred znaš ID dokumenta koji pregledaš)
    this.verzijaService.getSveVerzijeAdminSve().subscribe({
      next: (data) => {
        const aktivne = data
          .filter(v => !v.deleted)
          .sort((a, b) => a.dokument.naziv.localeCompare(b.dokument.naziv));
        const obrisane = data
          .filter(v => v.deleted)
          .sort((a, b) => a.dokument.naziv.localeCompare(b.dokument.naziv));
        this.verzije = [...aktivne, ...obrisane];
      },
      error: (err) => console.error('Greška pri učitavanju verzija:', err),
    });
  }

  dodajVerziju() {
  this.router.navigate(['/sluzbenik/verzije/add']);
}


  deaktiviraj(id: number) {
    this.verzijaService.deaktiviraj(id).subscribe(() => this.ucitajVerzije());
  }

  aktiviraj(id: number) {
    this.verzijaService.reaktiviraj(id).subscribe(() => this.ucitajVerzije());
  }

  goBack() {
    this.router.navigate(['/sluzbenik']);
  }
}
