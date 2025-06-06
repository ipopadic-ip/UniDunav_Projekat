import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TerminNastaveService, Profesor, ProfesorPredmet, TerminNastaveDTO } from '../../../core/services/termin-nastave.service';

@Component({
  selector: 'app-termini-nastave',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './termin-nastave.component.html',
  styleUrls: ['./termin-nastave.component.css']
})
export class TerminiNastaveComponent implements OnInit {
  form!: FormGroup;
  pretraga: string = '';

  sviProfesori: Profesor[] = [];
  filtriraniProfesori: Profesor[] = [];
  izabraniProfesor: Profesor | null = null;

  predmeti: ProfesorPredmet[] = [];

  constructor(
    private fb: FormBuilder,
    private terminService: TerminNastaveService
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      profesorPredmetId: ['', Validators.required],
      ishod: ['', Validators.required],
      terminPocetka: ['', Validators.required],
      terminZavrsetka: ['', Validators.required]
    });

    this.ucitajProfesore();
  }

  ucitajProfesore(): void {
    this.terminService.getProfesori().subscribe(data => {
      this.sviProfesori = data;
      this.filtriraniProfesori = [];
    });
  }

  pretraziProfesore(): void {
    const pojam = this.pretraga.trim().toLowerCase();
    if (!pojam) {
      this.filtriraniProfesori = [];
      return;
    }

    this.filtriraniProfesori = this.sviProfesori.filter(p =>
      (`${p.ime} ${p.prezime}`).toLowerCase().includes(pojam)
    );
  }

  odaberiProfesora(profesor: Profesor): void {
    this.izabraniProfesor = profesor;
    this.filtriraniProfesori = [];
    this.pretraga = `${profesor.ime} ${profesor.prezime}`;

    this.terminService.getPredmetiZaProfesora(profesor.id)
      .subscribe(data => this.predmeti = data);

    this.form.get('profesorPredmetId')?.setValue('');
  }

  sacuvaj(): void {
    if (this.form.valid) {
      const dto: TerminNastaveDTO = this.form.value;
      this.terminService.kreirajTermin(dto).subscribe({
        next: () => {
          alert('Uspešno kreiran termin nastave!');
          this.form.reset({
            profesorPredmetId: '',
            ishod: '',
            terminPocetka: '',
            terminZavrsetka: ''
          });
          this.predmeti = [];
          this.izabraniProfesor = null;
          this.pretraga = '';
          this.filtriraniProfesori = [];
        },
        error: err => alert('Greška: ' + (err.error?.message || 'Nepoznata greška'))
      });
    }
  }
}