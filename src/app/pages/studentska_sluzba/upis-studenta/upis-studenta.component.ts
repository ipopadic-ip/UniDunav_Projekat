import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {  ReactiveFormsModule,FormBuilder, FormGroup } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-upis-studenta',
  standalone: true,
  imports: [CommonModule,
  FormsModule,
  ReactiveFormsModule,
  HttpClientModule], 
  templateUrl: './upis-studenta.component.html',
  styleUrls: ['./upis-studenta.component.css']
})
export class UpisStudentaComponent implements OnInit {
  pretragaForma!: FormGroup;
  student: any = null;
  predmeti: any[] = [];
  izabraniPredmeti: Set<number> = new Set();

  constructor(private fb: FormBuilder, private http: HttpClient) {}
  pretragaPokusana: boolean = false;
  ngOnInit(): void {
    this.pretragaForma = this.fb.group({
      brojIndeksa: ['']
    });

    this.ucitajSvePredmete();
  }

  ucitajSvePredmete(): void {
    this.http.get<any[]>('/api/predmeti').subscribe({
      next: data => this.predmeti = data,
      error: err => console.error('Greška prilikom učitavanja predmeta', err)
    });
  }

  pretraziStudenta(): void {
  this.pretragaPokusana = true;
  const indeks = this.pretragaForma.value.brojIndeksa;
  this.http.get<any[]>(`/api/student/pretraga?indeks=${indeks}`).subscribe({
    next: data => this.student = data.length > 0 ? data[0] : null,
    error: () => this.student = null
  });
}

  togglePredmet(id: number): void {
    if (this.izabraniPredmeti.has(id)) {
      this.izabraniPredmeti.delete(id);
    } else {
      this.izabraniPredmeti.add(id);
    }
  }

  upisiStudenta(): void {
    if (!this.student || this.izabraniPredmeti.size === 0) return;

    const payload = {
      studentId: this.student.id,
      predmetIds: Array.from(this.izabraniPredmeti)
    };

    this.http.post('/api/pohadjanja/upisi', payload).subscribe({
      next: () => {
        alert('Uspešno upisan student!');
        this.izabraniPredmeti.clear();
      },
      error: () => alert('Greška prilikom upisa')
    });
  }
}