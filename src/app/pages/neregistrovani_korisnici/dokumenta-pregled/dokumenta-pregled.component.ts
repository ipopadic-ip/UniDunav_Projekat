import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { VerzijaDokumentaService } from '../../../core/services/verzija-dokumenta.service';
import { VerzijaDokumenta } from '../../../core/model/verzija-dokumenta.model';

@Component({
  selector: 'app-dokumenta-pregled',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dokumenta-pregled.component.html',
  styleUrls: ['./dokumenta-pregled.component.css']
})
export class DokumentaPregledComponent implements OnInit {
  verzije: VerzijaDokumenta[] = [];
  loading = true;

  constructor(private verzijaService: VerzijaDokumentaService) {}

  ngOnInit(): void {
    this.verzijaService.getPoslednjeVerzije().subscribe({
      next: data => {
        this.verzije = data;
        this.loading = false;
      },
      error: () => {
        alert('Greška pri učitavanju dokumenata.');
        this.loading = false;
      }
    });
  }
}