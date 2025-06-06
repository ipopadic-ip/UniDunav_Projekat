import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { TrebovanjeKnjigaService } from '../../../core/services/trebovanje-knjiga.service';

@Component({
  selector: 'app-trebovanje-knjiga',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './trebovanje-knjiga.component.html',
  styleUrls: ['./trebovanje-knjiga.component.css']
})
export class TrebovanjeKnjigaComponent implements OnInit {
  form!: FormGroup;
  knjige: any[] = [];

  constructor(
    private fb: FormBuilder,
    private knjigaService: TrebovanjeKnjigaService
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      naziv: ['', Validators.required],
      autor: ['', Validators.required],
      zanr: [''],
      godinaIzdavanja: [2024, Validators.required],
      izdavac: [''],
      brojPrimeraka: [1, [Validators.required, Validators.min(1)]]
    });

    this.ucitajKnjige();
  }

  ucitajKnjige(): void {
    this.knjigaService.getAll().subscribe(data => {
      this.knjige = data;
    });
  }

  potvrdi(): void {
    if (this.form.invalid) return;

    this.knjigaService.create(this.form.value).subscribe({
      next: () => {
        this.form.reset();
        this.form.patchValue({ godinaIzdavanja: 2024, brojPrimeraka: 1 });
        this.ucitajKnjige();
      },
      error: err => console.error(err)
    });
  }
}