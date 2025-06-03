import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { TrebovanjeService } from '../../../core/services/trebovanje.service';

@Component({
  selector: 'app-trebovanje',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './trebovanje.component.html',
  styleUrls: ['./trebovanje.component.css']
})
export class TrebovanjeComponent implements OnInit {
  form!: FormGroup;
  trebovanja: any[] = [];

  constructor(
    private fb: FormBuilder,
    private trebovanjeService: TrebovanjeService
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      nazivStavke: ['', Validators.required],
      kolicina: [1, [Validators.required, Validators.min(1)]]
    });

    this.ucitajTrebovanja();
  }

  ucitajTrebovanja(): void {
    this.trebovanjeService.getAll().subscribe(data => {
      this.trebovanja = data;
    });
  }

  potvrdi(): void {
    if (this.form.invalid) return;

    this.trebovanjeService.create(this.form.value).subscribe({
      next: () => {
        this.form.reset();
        this.form.patchValue({ kolicina: 1 });
        this.ucitajTrebovanja();
      },
      error: err => console.error(err)
    });
  }
}