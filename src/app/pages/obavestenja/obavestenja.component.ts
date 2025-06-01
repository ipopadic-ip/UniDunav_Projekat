import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../core/services/auth.service';
import { HttpClient } from '@angular/common/http';

interface ObavestenjeStudentu {
  predmetNaziv: string;
  tekst: string;
  datum: string;
}

@Component({
  selector: 'app-obavestenja',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './obavestenja.component.html',
  styleUrls: ['./obavestenja.component.css']
})
export class ObavestenjaComponent implements OnInit {
  obavestenja: ObavestenjeStudentu[] = [];
  grupisana: { [predmetNaziv: string]: ObavestenjeStudentu[] } = {};

  constructor(
    private authService: AuthService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    const studentId = this.authService.getLoggedInUserId();
    this.http.get<ObavestenjeStudentu[]>(`/api/student/${studentId}/obavestenja`).subscribe({
      next: (data) => {
        this.obavestenja = data;
        this.grupisana = this.grupisiPoPredmetu(data);
      },
      error: (err) => console.error('Greška prilikom učitavanja obaveštenja', err)
    });
  }

  grupisiPoPredmetu(data: ObavestenjeStudentu[]) {
    const mapa: { [predmetNaziv: string]: ObavestenjeStudentu[] } = {};
    data.forEach(o => {
      if (!mapa[o.predmetNaziv]) {
        mapa[o.predmetNaziv] = [];
      }
      mapa[o.predmetNaziv].push(o);
    });
    return mapa;
  }
}