import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { PredmetService } from '../../../../core/services/predmet.service';
import { GodinaStudijaService } from '../../../../core/services/godina-studija.service';
import { Predmet } from '../../../../core/model/predmet.model';
import { GodinaStudija } from '../../../../core/model/godina-studija.model';


@Component({
  selector: 'app-predmet-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './predmet-form.component.html',
})
export class PredmetFormComponent {
  private predmetService = inject(PredmetService);
  private godinaStudijaService = inject(GodinaStudijaService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  predmet: Partial<Predmet> = {};
  godineStudija: { id: number; godinaISmer: string }[] = [];

  godinaStudijaId: number | null = null;
  isEdit = false;

  ngOnInit() {
    this.godinaStudijaService.getAll().subscribe({
    next: (data: GodinaStudija[]) => {
      this.godineStudija = data.map(godina => ({
        id: godina.id,
        godinaISmer: `${godina.godina} - ${godina.studijskiProgramNaziv}`
      }));
    },
    error: () => alert('Greška pri učitavanju godina studija.'),
  });
    // this.godinaStudijaService.getAll().subscribe({
    //   next: (data) => {
    //     this.godineStudija = data;
    //   },
    //   error: () => alert('Greška pri učitavanju godina studija.'),
    // });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.predmetService.getPredmetById(+id).subscribe({
        next: (data) => {
          this.predmet = data;
          this.godinaStudijaId = data.godinaStudijaId;
        },
        error: () => alert('Greška pri učitavanju predmeta.'),
      });
    }
  }

  sacuvaj() {
    if (!this.godinaStudijaId) {
      alert('Izaberite godinu studija.');
      return;
    }

    const dto: Predmet = {
      ...this.predmet,
      godinaStudijaId: this.godinaStudijaId,
      deleted: false,
    } as Predmet;

    if (this.isEdit && this.predmet.id) {
      this.predmetService.updatePredmet(this.predmet.id, dto).subscribe({
        next: () => {
          alert('Predmet uspešno izmenjen.');
          this.router.navigate(['/admin/predmet']);
        },
        error: () => alert('Greška pri izmeni predmeta.'),
      });
    } else {
      this.predmetService.createPredmet(dto).subscribe({
        next: () => {
          alert('Predmet uspešno dodat.');
          this.router.navigate(['/admin/predmet']);
        },
        error: () => alert('Greška pri dodavanju predmeta.'),
      });
    }
  }

  otkazi() {
    this.router.navigate(['/admin/predmet']);
  }

  goBack() {
    this.router.navigate(['/admin/predmet']);
  }
}
