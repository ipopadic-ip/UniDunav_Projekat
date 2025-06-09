import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { PredmetService } from '../../../../core/services/predmet.service';
import { Predmet } from '../../../../core/model/predmet.model';

@Component({
  selector: 'app-predmet-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './predmet-list.component.html',
  styleUrls: ['./predmet-list.component.css']
})
export class PredmetListComponent implements OnInit {
  private predmetService = inject(PredmetService);
  private router = inject(Router);

  predmeti: Predmet[] = [];

  grupisaniPredmeti: { smer: string, predmeti: Predmet[] }[] = [];


  ngOnInit(): void {
    this.ucitajPredmete();
  }

  ucitajPredmete() {
  this.predmetService.getAllPredmetiAdmin().subscribe({
    next: (data) => {
      const aktivni = data.filter(p => !p.deleted);
      const izbrisani = data.filter(p => p.deleted);

      const grupisani = this.grupisiPoSmeru(aktivni);

      // Dodaj izbrisane predmete na kraj (grupisane pod posebnom grupom)
      if (izbrisani.length > 0) {
        grupisani.push({
          smer: '🗑️ Izbrisani predmeti',
          predmeti: izbrisani
        });
      }

      this.grupisaniPredmeti = grupisani;
    },
    error: (err) => console.error('Greška pri učitavanju predmeta:', err)
  });
}

grupisiPoSmeru(predmeti: Predmet[]): { smer: string, predmeti: Predmet[] }[] {
  const mapa = new Map<string, Predmet[]>();

  for (const p of predmeti) {
    const smer = p.godinaISmer.split(' - ')[1]?.trim() || 'Nepoznat smer';
    if (!mapa.has(smer)) {
      mapa.set(smer, []);
    }
    mapa.get(smer)!.push(p);
  }

  return Array.from(mapa.entries())
    .sort((a, b) => a[0].localeCompare(b[0]))
    .map(([smer, predmeti]) => ({
      smer,
      predmeti: predmeti.sort((a, b) => a.godinaStudijaId - b.godinaStudijaId) // sortiranje po godini
    }));
    // .map(([smer, predmeti]) => ({ smer, predmeti }));
}

  deaktivirajPredmet(id: number) {
    this.predmetService.deaktivirajPredmet(id).subscribe({
      next: () => this.ucitajPredmete(),
      error: (err) => console.error('Greška pri deaktivaciji:', err)
    });
  }

  aktivirajPredmet(id: number) {
    this.predmetService.aktivirajPredmet(id).subscribe({
      next: () => this.ucitajPredmete(),
      error: (err) => console.error('Greška pri aktivaciji:', err)
    });
  }

  dodajPredmet() {
    this.router.navigate(['/admin/predmet/add']);
  }

  izmeniPredmet(id: number) {
    this.router.navigate(['/admin/predmet/edit', id]);
  }

  goBack() {
    this.router.navigate(['/admin']);
  }
}
