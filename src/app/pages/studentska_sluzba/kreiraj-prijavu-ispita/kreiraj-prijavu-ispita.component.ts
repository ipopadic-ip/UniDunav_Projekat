import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { PrijavaIspitaService } from '../../../core/services/prijava-ispita.service';
import { PredmetService } from '../../../core/services/predmet.service';
import { Predmet } from '../../../core/model/predmet.model';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-kreiraj-prijavu-ispita',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule, RouterModule],
  templateUrl: './kreiraj-prijavu-ispita.component.html',
  styleUrls: ['./kreiraj-prijavu-ispita.component.css']
})
export class KreirajPrijavuIspitaComponent {
  form: FormGroup;
  successMessage = '';
  errorMessage = '';
  predmeti: Predmet[] = [];

  constructor(
    private fb: FormBuilder,
    private prijavaService: PrijavaIspitaService,
    private predmetService: PredmetService
  ) {
    this.form = this.fb.group({
      predmetId: [null, Validators.required],
      datumIspita: ['', Validators.required]
    });

    // Učitaj sve predmete
    this.predmetService.getSviPredmeti().subscribe({
      next: (data) => this.predmeti = data,
      error: () => this.errorMessage = 'Greška pri učitavanju predmeta.'
    });
  }

  onSubmit(): void {
    if (this.form.invalid) return;

    const dto = {
      predmetId: this.form.value.predmetId,
      datumIspita: this.form.value.datumIspita
    };

    this.prijavaService.kreirajPrijave(dto).subscribe({
      next: (res: string) => {
        this.successMessage = res || 'Uspešno kreirane prijave ispita.';
        this.errorMessage = '';
        this.form.reset();
      },
      error: (err) => {
        this.successMessage = '';
        this.errorMessage = 'Greška prilikom kreiranja prijava: ' + (err.error || 'Nepoznata greška.');
      }
    });
  }
}