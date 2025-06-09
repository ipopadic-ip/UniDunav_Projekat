import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-opsta-obavestenja',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './opsta-obavestenja.component.html',
  styleUrls: ['./opsta-obavestenja.component.css']
})
export class OpstaObavestenjaComponent implements OnInit {
  obavestenja: any[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
  this.http.get<any[]>('/api/opsta-obavestenja').subscribe({
    next: data => {
      this.obavestenja = data.sort((a, b) => new Date(b.datum).getTime() - new Date(a.datum).getTime());
    },
    error: err => console.error('Greška pri učitavanju obaveštenja', err)
  });
}
  }