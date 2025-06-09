import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProfesorService } from '../../../../core/services/profesor.service';
import { DepartmanService } from '../../../../core/services/departman.service';
import { KatedraService } from '../../../../core/services/katedra.service';
import { Profesor } from '../../../../core/model/profesor.model';
import { Departman } from '../../../../core/model/departman.model';
import { Katedra } from '../../../../core/model/katedra.model';

@Component({
  selector: 'app-katedra-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './katedra-form.component.html',
})
export class KatedraFormComponent {
  private katedraService = inject(KatedraService);
  private profesorService = inject(ProfesorService);
  private departmanService = inject(DepartmanService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  katedra: Partial<Katedra> = {};
  profesori: Profesor[] = [];
  departmani: Departman[] = [];

  profesorId: number | null = null;
  departmanId: number | null = null;
  isEdit = false;

  ngOnInit() {
    this.profesorService.getAllProfesori().subscribe({
      // next: (data) => this.profesori = data,
      next: (data) => {
        this.profesori = data.sort((a, b) =>
          (a.ime + a.prezime).localeCompare(b.ime + b.prezime)
        );
      },
      error: (err) => console.error('Greška pri učitavanju profesora', err),
    });

    this.departmanService.getAllDepartmani().subscribe({
      next: (data) => this.departmani = data,
      error: (err) => console.error('Greška pri učitavanju departmana', err),
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.katedraService.getKatedraById(+id).subscribe({
        next: (data) => {
          this.katedra = data;
          this.profesorId = data.sefKatedre?.id ?? null;
          this.departmanId = data.departman?.id ?? null;
        },
        error: (err) => console.error('Greška pri učitavanju katedre', err),
      });
    }
  }

  sacuvaj() {
    if (!this.profesorId || !this.departmanId) {
      alert('Morate izabrati i šefa i departman.');
      return;
    }

    const dto = {
      ...this.katedra,
      sefKatedreId: this.profesorId,
      departmanId: this.departmanId,
    };

    if (this.isEdit && this.katedra.id) {
      this.katedraService.updateKatedra(this.katedra.id, dto).subscribe({
        next: () => {
          alert('Katedra uspešno izmenjena.');
          this.router.navigate(['/admin/katedra']);
        },
        error: () => alert('Greška pri izmeni katedre.'),
      });
    } else {
      this.katedraService.createKatedra(dto).subscribe({
        next: () => {
          alert('Katedra uspešno dodata.');
          this.router.navigate(['/admin/katedra']);
        },
        error: () => alert('Greška pri dodavanju katedre.'),
      });
    }
  }

  goBack() {
    this.router.navigate(['/admin/katedra']);
  }

  otkazi() {
    this.router.navigate(['/admin/katedra']);
  }
}
