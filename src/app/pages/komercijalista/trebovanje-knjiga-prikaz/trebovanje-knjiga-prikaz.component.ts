import { Component, OnInit } from '@angular/core';
import { TrebovanjeKnjigaService } from '../../../core/services/trebovanje-knjiga.service';
import { CommonModule } from '@angular/common'; 

@Component({
  selector: 'app-trebovanje-knjiga-prikaz',
  templateUrl: './trebovanje-knjiga-prikaz.component.html',
  styleUrls: ['./trebovanje-knjiga-prikaz.component.css'],
  imports: [CommonModule]
})
export class TrebovanjeKnjigaPrikazComponent implements OnInit {
  trebovanja: any[] = [];
  potvrđenaTrebovanja: number[] = [];

  constructor(private trebovanjeService: TrebovanjeKnjigaService) {}

  ngOnInit(): void {
    this.ucitajTrebovanja();
  }

  ucitajTrebovanja(): void {
  this.trebovanjeService.getAll().subscribe({
    next: (data) => {
      this.trebovanja = data.sort((a, b) =>
  new Date(b.datumTrebovanja).getTime() - new Date(a.datumTrebovanja).getTime()
);

    },
    error: (err) => console.error('Greška prilikom učitavanja trebovanja knjiga', err)
  });
}

  potvrdi(id: number): void {
    this.trebovanjeService.potvrdiTrebovanje(id).subscribe({
      next: () => {
        this.potvrđenaTrebovanja.push(id);
      },
      error: (err) => console.error('Greška prilikom potvrđivanja', err)
    });
  }

  jePotvrdjeno(id: number): boolean {
    return this.potvrđenaTrebovanja.includes(id);
  }
}