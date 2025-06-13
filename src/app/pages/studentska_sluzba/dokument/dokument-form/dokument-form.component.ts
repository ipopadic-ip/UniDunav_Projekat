import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DokumentService } from '../../../../core/services/dokument.service';
import { Dokument } from '../../../../core/model/dokument.model';

@Component({
  selector: 'app-dokument-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './dokument-form.component.html',
})
export class DokumentFormComponent {
  private dokumentService = inject(DokumentService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  dokument: Partial<Dokument> = {};
  isEdit = false;

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEdit = true;
      this.dokumentService.getDokumentById(+id).subscribe({
        next: (data) => (this.dokument = data),
        error: (err) => console.error('Greška pri učitavanju dokumenta', err),
      });
    }
  }

  sacuvaj() {
    if (!this.dokument.naziv) {
      alert('Naziv je obavezni.');
      return;
    }

    if (this.isEdit && this.dokument.id) {
      this.dokumentService.updateDokument(this.dokument.id, this.dokument).subscribe({
        next: () => {
          alert('Dokument uspešno izmenjen.');
          this.router.navigate(['/sluzbenik/dokument']);
        },
        error: () => alert('Greška pri izmeni dokumenta.'),
      });
    } else {
      this.dokumentService.createDokument(this.dokument).subscribe({
        next: () => {
          alert('Dokument uspešno dodat.');
          this.router.navigate(['/sluzbenik/dokument']);
        },
        error: () => alert('Greška pri dodavanju dokumenta.'),
      });
    }
  }

  goBack() {
    this.router.navigate(['/sluzbenik/dokument']);
  }

  otkazi() {
    this.router.navigate(['/sluzbenik/dokument']);
  }
}
