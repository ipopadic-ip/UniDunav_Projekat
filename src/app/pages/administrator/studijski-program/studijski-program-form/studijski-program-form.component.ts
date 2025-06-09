import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfesorService } from '../../../../core/services/profesor.service';
import { KatedraService } from '../../../../core/services/katedra.service';
import { TipStudijaService } from '../../../../core/services/tip-studija.service';
import { StudijskiProgramService } from '../../../../core/services/studijski-program.service';
import { Profesor } from '../../../../core/model/profesor.model';
import { Katedra } from '../../../../core/model/katedra.model';
// import { TipStudija } from '../../../../core/model/tip-studija.model';
import { TipStudijaRDF } from '../../../../core/model/TipStudijaRDF.model';
import { StudijskiProgram } from '../../../../core/model/studijski-program.model';

@Component({
  selector: 'app-studijski-program-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './studijski-program-form.component.html',
})
export class StudijskiProgramFormComponent {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private studijskiProgramService = inject(StudijskiProgramService);
  private profesorService = inject(ProfesorService);
  private katedraService = inject(KatedraService);
  private tipStudijaService = inject(TipStudijaService);

  isEdit = false;
  studijskiProgram: Partial<StudijskiProgram> = {};

  profesori: Profesor[] = [];
  katedre: Katedra[] = [];
  // tipoviStudija: TipStudija[] = [];
    tipoviStudija: TipStudijaRDF[] = [];


  profesorId: number | null = null;
  katedraId: number | null = null;
  tipStudijaId: number | null = null;
  tipStudijaUri: string | null = null;

  

  ngOnInit() {
    this.profesorService.getAllProfesori().subscribe({
      // next: data => this.profesori = data,
      next: (data) => {
        this.profesori = data.sort((a, b) =>
          (a.ime + a.prezime).localeCompare(b.ime + b.prezime)
        );
      },
      error: err => console.error('Greška pri učitavanju profesora', err),
    });

    this.katedraService.getAllKatedre().subscribe({
      next: data => this.katedre = data,
      error: err => console.error('Greška pri učitavanju katedri', err),
    });

    

    this.tipStudijaService.getAll().subscribe({
      next: data => this.tipoviStudija = data,

      error: err => console.error('Greška pri učitavanju tipova studija', err),
    });


    // this.tipStudijaService.getAll().subscribe({
    //   next: data => this.tipoviStudija = data,
    //   error: err => console.error('Greška pri učitavanju tipova studija', err),
    // });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.studijskiProgramService.getStudijskiProgramById(+id).subscribe({
        next: (data) => {
          this.studijskiProgram = data;
          this.profesorId = data.rukovodilac?.id ?? null;
          this.katedraId = data.katedraId;
          // this.tipStudijaId = data.tipStudija?.id ?? null;
          this.tipStudijaUri = data.tipStudija?.uri ?? null;

        },
        error: (err) => console.error('Greška pri učitavanju studijskog programa', err),
      });
    }
  }

  sacuvaj() {
    if (!this.profesorId || !this.katedraId || !this.tipStudijaUri) {
      alert('Popunite sva obavezna polja.');
      return;
    }

    const dto = {
      ...this.studijskiProgram,
      rukovodilacId: this.profesorId,
      katedraId: this.katedraId,
      tipStudijaUri: this.tipStudijaUri
      // tipStudijaId: this.tipStudijaId,
    };

    if (this.isEdit && this.studijskiProgram.id) {
      this.studijskiProgramService.update(this.studijskiProgram.id, dto).subscribe({
        next: () => {
          alert('Studijski program uspešno izmenjen.');
          this.router.navigate(['/admin/studijski-program']);
        },
        error: () => alert('Greška pri izmeni.'),
      });
    } else {
      this.studijskiProgramService.create(dto).subscribe({
        next: () => {
          alert('Studijski program uspešno dodat.');
          this.router.navigate(['/admin/studijski-program']);
        },
        error: () => alert('Greška pri dodavanju.'),
      });
    }
  }

  goBack() {
    this.router.navigate(['/admin/studijski-program']);
  }

  otkazi() {
    this.router.navigate(['/admin/studijski-program']);
  }
}
