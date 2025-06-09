import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Fakultet } from '../../../../core/model/fakultet.model'; 
import { FakultetService } from '../../../../core/services/fakultet.service';
import { Profesor } from '../../../../core/model/profesor.model';
import { ProfesorService } from '../../../../core/services/profesor.service';

@Component({
  selector: 'app-fakultet-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './fakultet-form.component.html',
})
export class FakultetFormComponent {
  private fakultetService = inject(FakultetService);
  private profesorService = inject(ProfesorService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  fakultet: Partial<Fakultet> = {};
  profesori: Profesor[] = [];
  profesorId: number | null = null;
  isEdit = false;

  ngOnInit() {
    this.profesorService.getAllProfesori().subscribe({
      next: (data) => {
        this.profesori = data.sort((a, b) =>
          (a.ime + a.prezime).localeCompare(b.ime + b.prezime)
        );
      },
      error: (err) => console.error('Greška pri učitavanju profesora', err),
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.fakultetService.getFakultetById(+id).subscribe({
        next: (data) => {
          this.fakultet = data;
          this.profesorId = data['dekanId'];
        },
        error: (err) => console.error('Greška pri učitavanju fakulteta', err),
      });
    }
  }

  sacuvaj() {
    if (!this.profesorId) {
      alert('Morate izabrati dekana.');
      return;
    }

    const dto = {
      ...this.fakultet,
      dekanId: this.profesorId,
    };

    if (this.isEdit && this.fakultet.id) {
      this.fakultetService.updateFakultet(this.fakultet.id, dto).subscribe({
        next: () => {
          alert('Fakultet uspešno izmenjen.');
          this.router.navigate(['/admin/fakultet']);
        },
        error: () => alert('Greška pri izmeni fakulteta.'),
      });
    } else {
      this.fakultetService.createFakultet(dto).subscribe({
        next: () => {
          alert('Fakultet uspešno dodat.');
          this.router.navigate(['/admin/fakultet']);
        },
        error: () => alert('Greška pri dodavanju fakulteta.'),
      });
    }
  }

  goBack(): void {
    this.router.navigate(['/admin/fakultet']);
  }

  otkazi() {
    this.router.navigate(['/admin/fakultet']);
  }
}
