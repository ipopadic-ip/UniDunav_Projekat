import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthService } from '../../../core/services/auth.service';
import { CommonModule } from '@angular/common';

interface EvaluacijaZnanja {
  id: number;
  vremePocetka: string;
  tipEvaluacijeId: number;
  pohadjanjeId: number;
  predmetNaziv: string;
  tipEvaluacijeNaziv: string;
  brojBodova: number | null;
}

@Component({
  selector: 'app-evaluacije',
  templateUrl: './evaluacije.component.html',
  styleUrls: ['./evaluacije.component.css'],
  imports: [CommonModule],
})
export class EvaluacijeComponent implements OnInit {
  evaluacije: EvaluacijaZnanja[] = [];
  studentId: number = 0;

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.studentId = this.authService.getLoggedInUserId();
    this.ucitajEvaluacije();
  }

  ucitajEvaluacije(): void {
  this.http.get<EvaluacijaZnanja[]>(`http://localhost:8080/api/evaluacija-znanja/student/${this.studentId}`)
    .subscribe(data => {
      this.evaluacije = data
        .filter(e => e.vremePocetka) // obrisi null vrednosti ako ih ima
        .sort((a, b) => new Date(b.vremePocetka).getTime() - new Date(a.vremePocetka).getTime());
    });
}

  skiniPdf(evaluacijaId: number): void {
  this.http.get(`http://localhost:8080/api/evaluacija-znanja/pdf/${evaluacijaId}`, {
    responseType: 'blob'
  }).subscribe(blob => {
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `evaluacija_${evaluacijaId}.pdf`;
    a.click();
    window.URL.revokeObjectURL(url);
  }, error => {
    console.error('Greška prilikom preuzimanja PDF-a:', error);
    alert('Neuspešno preuzimanje PDF fajla.');
  });
}
}