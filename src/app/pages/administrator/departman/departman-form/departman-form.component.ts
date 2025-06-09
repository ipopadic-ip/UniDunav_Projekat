import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Departman } from '../../../../core/model/departman.model';
import { DepartmanService } from '../../../../core/services/departman.service';
import { ProfesorService } from '../../../../core/services/profesor.service';
import { FakultetService } from '../../../../core/services/fakultet.service';
import { Profesor } from '../../../../core/model/profesor.model';
import { Fakultet } from '../../../../core/model/fakultet.model';

@Component({
  selector: 'app-departman-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './departman-form.component.html',
})
export class DepartmanFormComponent {
  private departmanService = inject(DepartmanService);
  private profesorService = inject(ProfesorService);
  private fakultetService = inject(FakultetService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  departman: Partial<Departman> = {};
  profesori: Profesor[] = [];
  fakulteti: Fakultet[] = [];

  profesorId: number | null = null;
  fakultetId: number | null = null;
  isEdit = false;

  ngOnInit() {
    // Učitaj profesore
    this.profesorService.getAllProfesori().subscribe({
      next: (data) => {
        this.profesori = data.sort((a, b) =>
          (a.ime + a.prezime).localeCompare(b.ime + b.prezime)
        );
      },
      error: (err) => console.error('Greška pri učitavanju profesora', err),
    });

    // Učitaj fakultete
    this.fakultetService.getFakulteti().subscribe({
      next: (data) => {
        this.fakulteti = data.sort((a, b) => a.naziv.localeCompare(b.naziv));
      },
      error: (err) => console.error('Greška pri učitavanju fakulteta', err),
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.departmanService.getDepartmanById(+id).subscribe({
        next: (data) => {
          this.departman = data;
          this.profesorId = data.sefDepartmana?.id ?? null;
          this.fakultetId = data.fakultet?.id ?? null;
        },
        error: (err) => console.error('Greška pri učitavanju departmana', err),
      });
    }
  }

  sacuvaj() {
    if (!this.profesorId || !this.fakultetId) {
      alert('Morate izabrati i šefa i fakultet.');
      return;
    }

    const dto = {
      ...this.departman,
      sefDepartmanaId: this.profesorId,
      fakultetId: this.fakultetId,
    };

    if (this.isEdit && this.departman.id) {
      this.departmanService.updateDepartman(this.departman.id, dto).subscribe({
        next: () => {
          alert('Departman uspešno izmenjen.');
          this.router.navigate(['/admin/departman']);
        },
        error: () => alert('Greška pri izmeni departmana.'),
      });
    } else {
      this.departmanService.createDepartman(dto).subscribe({
        next: () => {
          alert('Departman uspešno dodat.');
          this.router.navigate(['/admin/departman']);
        },
        error: () => alert('Greška pri dodavanju departmana.'),
      });
    }
  }

  goBack() {
    this.router.navigate(['/admin/departman']);
  }

  otkazi() {
    this.router.navigate(['/admin/departman']);
  }
}
