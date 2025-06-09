import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfesorService } from '../../../../core/services/profesor.service';
import { PredmetService } from '../../../../core/services/predmet.service';
import { ProfesorPredmetService } from '../../../../core/services/profesor-predmet.service';
import { Profesor } from '../../../../core/model/profesor.model';
import { Predmet } from '../../../../core/model/predmet.model';
import { ProfesorPredmet } from '../../../../core/model/profesor-predmet.model';

import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-profesor-predmet-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './profesor-predmet-form.component.html',
})
export class ProfesorPredmetFormComponent {
  private profesorService = inject(ProfesorService);
  private predmetService = inject(PredmetService);
  private ppService = inject(ProfesorPredmetService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  profesori: Profesor[] = [];
  predmeti: Predmet[] = [];
  profesorPredmet: Partial<ProfesorPredmet> = {};

  profesorId: number | null = null;
  predmetId: number | null = null;
  isEdit = false;


ngOnInit() {
  const id = this.route.snapshot.paramMap.get('id');
  this.isEdit = !!id;

  // Učitavamo profesore i predmete paralelno
  const profesori$ = this.profesorService.getAllProfesori();
  const predmeti$ = this.predmetService.getAll();

  if (this.isEdit && id) {
    const profesorPredmet$ = this.ppService.getProfesorPredmetById(+id);

    forkJoin([profesori$, predmeti$, profesorPredmet$]).subscribe(([profesori, predmeti, pp]) => {
      this.profesori = profesori.sort((a, b) => (a.ime + a.prezime).localeCompare(b.ime + b.prezime));
      this.predmeti = predmeti.sort((a, b) => a.naziv.localeCompare(b.naziv));
      this.profesorPredmet = pp;
      this.profesorId = pp.profesorId ?? null;
      this.predmetId = pp.predmetId ?? null;
    });
  } else {
    // Ako nije edit mod, samo učitaj profesore i predmete
    forkJoin([profesori$, predmeti$]).subscribe(([profesori, predmeti]) => {
      this.profesori = profesori.sort((a, b) => (a.ime + a.prezime).localeCompare(b.ime + b.prezime));
      this.predmeti = predmeti.sort((a, b) => a.naziv.localeCompare(b.naziv));
    });
  }
}


  // ngOnInit() {
  //   this.profesorService.getAllProfesori().subscribe((data) => {
  //     this.profesori = data.sort((a, b) => (a.ime + a.prezime).localeCompare(b.ime + b.prezime));
  //   });

  //   this.predmetService.getAll().subscribe((data) => {
  //     this.predmeti = data.sort((a, b) => a.naziv.localeCompare(b.naziv));
  //   });

  //   const id = this.route.snapshot.paramMap.get('id');
  //   if (id) {
  //     this.isEdit = true;
  //     this.ppService.getProfesorPredmetById(+id).subscribe((data) => {
  //       this.profesorPredmet = data;
  //       this.profesorId = data.profesorId ?? null;
  //       this.predmetId = data.predmetId ?? null;
  //     });
  //   }
  // }

  sacuvaj() {
    if (!this.profesorId || !this.predmetId) {
      alert('Morate izabrati profesora i predmet.');
      return;
    }

    const dto = {
      profesorId: this.profesorId,
      predmetId: this.predmetId,
    };

    if (this.isEdit && this.profesorPredmet.id) {
      this.ppService.updateProfesorPredmet(this.profesorPredmet.id, dto).subscribe({
        next: () => {
          alert('Veza uspešno izmenjena.');
          this.router.navigate(['/admin/profesor-predmet']);
        },
        error: () => alert('Greška pri izmeni.'),
      });
    } else {
      this.ppService.createProfesorPredmet(dto).subscribe({
        next: () => {
          alert('Veza uspešno dodata.');
          this.router.navigate(['/admin/profesor-predmet']);
        },
        error: () => alert('Greška pri dodavanju.'),
      });
    }
  }

  goBack() {
    this.router.navigate(['/admin/profesor-predmet']);
  }

  otkazi() {
    this.router.navigate(['/admin/profesor-predmet']);
  }
}
