import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TrebovanjeService } from '../../../core/services/trebovanje.service';

@Component({
  selector: 'app-trebovanje-prikaz',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './trebovanje-prikaz.component.html',
  styleUrls: ['./trebovanje-prikaz.component.css']
})
export class TrebovanjePrikazComponent implements OnInit {
  trebovanja: any[] = [];
  potvrđenaTrebovanja: number[] = [];

  constructor(private trebovanjeService: TrebovanjeService) {}

  ngOnInit(): void {
    this.ucitajTrebovanja();
  }

  ucitajTrebovanja(): void {
    this.trebovanjeService.getAll().subscribe({
      next: (data) => {
        this.trebovanja = data
          .filter(t => t.datumTrebovanja)
          .sort((a, b) => new Date(b.datumTrebovanja).getTime() - new Date(a.datumTrebovanja).getTime());
      },
      error: (err) => console.error('Greška prilikom učitavanja trebovanja', err)
    });
  }

  potvrdi(id: number): void {
    this.trebovanjeService.potvrdiTrebovanje(id).subscribe({
      next: () => this.potvrđenaTrebovanja.push(id),
      error: (err) => console.error('Greška prilikom potvrđivanja', err)
    });
  }

  jePotvrdjeno(id: number): boolean {
    return this.potvrđenaTrebovanja.includes(id);
  }
}