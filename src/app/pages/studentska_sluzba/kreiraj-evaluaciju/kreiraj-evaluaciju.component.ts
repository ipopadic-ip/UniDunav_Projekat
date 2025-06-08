import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { EvaluacijaService } from '../../../core/services/evaluacija.service';
import { PredmetService } from '../../../core/services/predmet.service';
import { TipEvaluacijeService } from '../../../core/services/tip-evaluacije.service';

@Component({
  selector: 'app-kreiraj-evaluaciju',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule, RouterModule],
  templateUrl: './kreiraj-evaluaciju.component.html',
  styleUrls: ['./kreiraj-evaluaciju.component.css']
})
export class KreirajEvaluacijuComponent implements OnInit {
  form!: FormGroup;
  predmeti: any[] = [];
  tipovi: any[] = [];
  successMessage = '';
  errorMessage = '';

  constructor(
    private fb: FormBuilder,
    private evaluacijaService: EvaluacijaService,
    private predmetService: PredmetService,
    private tipService: TipEvaluacijeService
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      predmetId: ['', Validators.required],
      tipEvaluacijeId: ['', Validators.required],
      vremePocetka: ['', Validators.required]
    });

    this.predmetService.getSviPredmeti().subscribe(data => this.predmeti = data);
    this.tipService.getAll().subscribe(data => this.tipovi = data);
  }

  onSubmit(): void {
    if (this.form.invalid) return;

    this.evaluacijaService.kreirajEvaluaciju(this.form.value).subscribe({
      next: () => {
        this.successMessage = 'Evaluacija uspešno kreirana.';
        this.errorMessage = '';
        this.form.reset();
      },
      error: () => {
        this.successMessage = '';
        this.errorMessage = 'Greška pri kreiranju evaluacije.';
      }
    });
  }
}