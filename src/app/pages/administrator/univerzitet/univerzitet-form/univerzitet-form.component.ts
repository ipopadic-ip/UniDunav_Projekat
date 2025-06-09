import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Univerzitet } from '../../../../core/model/univerzitet.model';
import { UniverzitetService } from '../../../../core/services/univerzitet.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Profesor } from '../../../../core/model/profesor.model';
import { ProfesorService } from '../../../../core/services/profesor.service';

@Component({
  selector: 'app-univerzitet-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './univerzitet-form.component.html',
})
export class UniverzitetFormComponent {
  private univerzitetService = inject(UniverzitetService);
  private http = inject(HttpClient);
  private router = inject(Router);

  univerzitet: Univerzitet | null = null;
  slike: File[] = [];
  profesori: Profesor[] = [];

  private profesorService = inject(ProfesorService);

  ngOnInit() {

    this.univerzitetService.getUniverzitet().subscribe({
      // next: (data) => this.univerzitet = data,
       next: (data) => {
        this.univerzitet = data;
        this.ucitajProfesore();
      },
      error: (err) => console.error('Greška pri učitavanju univerziteta', err)
    });
  }

   ucitajProfesore() {
    this.profesorService.getAllProfesori().subscribe({
      next: (data) => {
        this.profesori = data.sort((a, b) =>
          (a.ime + a.prezime).localeCompare(b.ime + b.prezime)
        );
      },
      error: (err) => console.error('Greška pri učitavanju profesora', err)
    });
  }

  sacuvajIzmene() {
    if (!this.univerzitet) return;

    this.univerzitetService.updateUniverzitet(this.univerzitet.id, this.univerzitet).subscribe({
      next: () => alert('Podaci uspešno sačuvani.'),
      error: () => alert('Greška pri čuvanju podataka.')
    });
  }

  goBack(): void {
    this.router.navigate(['/admin']);
  }

  onFileSelected(event: any) {
    const files = event.target.files;
    if (files.length === 2) {
      this.slike = [files[0], files[1]];
    } else {
      alert('Morate odabrati tačno dve slike.');
    }
  }

  sacuvajSlike() {
  if (!this.univerzitet || this.slike.length !== 2) {
    alert('Odaberite tačno dve slike.');
    return;
  }

  const formData = new FormData();
  this.slike.forEach(file => formData.append('slike', file));

  this.http.post(`http://localhost:8080/api/univerziteti/${this.univerzitet.id}/slike`, formData, { responseType: 'text' }).subscribe({
    next: (res) => {
      alert(res); // backend poruka: "Slike uspešno sačuvane."

      // Automatsko ažuriranje prikaza slika bez reload
      const timestamp = new Date().getTime();
      if (this.univerzitet) {
        this.univerzitet.slika1Path = `uploads/univerziteti/univerzitet_${this.univerzitet.id}_1_${timestamp}_${this.slike[0].name}`;
        this.univerzitet.slika2Path = `uploads/univerziteti/univerzitet_${this.univerzitet.id}_2_${timestamp}_${this.slike[1].name}`;
      }
    },
    error: (err) => {
      console.error(err);
      alert('Greška pri slanju slika.');
    }
  });
}


  // sacuvajSlike() {
  //   if (!this.univerzitet || this.slike.length !== 2) {
  //     alert('Odaberite tačno dve slike.');
  //     return;
  //   }

  //   const formData = new FormData();
  //   this.slike.forEach(file => formData.append('slike', file));

  //   this.http.post(`http://localhost:8080/api/univerziteti/${this.univerzitet.id}/slike`, formData).subscribe({
  //     next: () => {
  //       alert('Slike uspešno sačuvane.');
  //       window.location.reload(); // osveži prikaz slika
  //     },
  //     error: () => alert('Greška pri slanju slika.')
  //   });
  // }
}
