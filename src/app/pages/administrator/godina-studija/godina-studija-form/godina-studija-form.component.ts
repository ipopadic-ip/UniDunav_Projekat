import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { GodinaStudijaService } from '../../../../core/services/godina-studija.service';
import { StudijskiProgramService } from '../../../../core/services/studijski-program.service';
import { GodinaStudija } from '../../../../core/model/godina-studija.model';
import { StudijskiProgram } from '../../../../core/model/studijski-program.model';

@Component({
  selector: 'app-godina-studija-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './godina-studija-form.component.html',
})
export class GodinaStudijaFormComponent {
  private godinaStudijaService = inject(GodinaStudijaService);
  private studijskiProgramService = inject(StudijskiProgramService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  godinaStudija: Partial<GodinaStudija> = {};
  studijskiProgrami: StudijskiProgram[] = [];
  isEdit = false;

  godina: number | null = null;
  studijskiProgramId: number | null = null;

  ngOnInit() {
    this.studijskiProgramService.getAll().subscribe({
      next: (data) => (this.studijskiProgrami = data),
      error: (err) => console.error('Greška pri učitavanju studijskih programa', err),
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.godinaStudijaService.getById(+id).subscribe({
        next: (data) => {
          this.godinaStudija = data;
          this.godina = data.godina;
          this.studijskiProgramId = data.studijskiProgramId;
        },
        error: (err) => console.error('Greška pri učitavanju godine studija', err),
      });
    }
  }

  sacuvaj() {
    if (this.godina === null || this.studijskiProgramId === null) {
      alert('Molimo unesite godinu i odaberite studijski program.');
      return;
    }

    const dto = {
      godina: this.godina,
      studijskiProgramId: this.studijskiProgramId,
    };

    if (this.isEdit && this.godinaStudija.id) {
      this.godinaStudijaService.update(this.godinaStudija.id, dto).subscribe({
        next: () => {
          alert('Godina studija uspešno izmenjena.');
          this.router.navigate(['/admin/godina-studija']);
        },
        error: () => alert('Greška pri izmeni godine studija.'),
      });
    } else {
      this.godinaStudijaService.create(dto).subscribe({
        next: () => {
          alert('Godina studija uspešno dodata.');
          this.router.navigate(['/admin/godina-studija']);
        },
        error: () => alert('Greška pri dodavanju godine studija.'),
      });
    }
  }

  goBack() {
    this.router.navigate(['/admin/godina-studija']);
  }

  otkazi() {
    this.router.navigate(['/admin/godina-studija']);
  }
}
