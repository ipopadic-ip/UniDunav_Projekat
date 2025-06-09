import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../../core/services/auth.service';
import { PrijavaIspitaDTO } from '../../../core/model/prijava-ispita.model';
import { PrijavaIspitaService } from '../../../core/services/prijava-ispita.service';

@Component({
  selector: 'app-prijava-ispita',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './prijava-ispita.component.html',
  styleUrls: ['./prijava-ispita.component.css']
})
export class PrijavaIspitaComponent implements OnInit {
  prijave: PrijavaIspitaDTO[] = [];
  studentId!: number;

  constructor(
    private prijavaService: PrijavaIspitaService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.studentId = this.authService.getLoggedInUserId();
    this.prijavaService.getDostupnePrijave(this.studentId).subscribe({
      next: (data) => {
        console.log('📦 Prijave stigle:', data);  // <-- obavezno prati ovo
        this.prijave = data;
      },
      error: (err) => console.error('❌ Greška pri učitavanju prijava', err)
    });
    //   next: (data) => this.prijave = data,
    //   error: (err) => console.error('Greška pri učitavanju prijava', err)
    // });
  }

  prijavi(prijavaId: number): void {
    this.prijavaService.prijavi(prijavaId).subscribe({
      next: () => {
        const prijava = this.prijave.find(p => p.id === prijavaId);
        if (prijava) {
          prijava.status = true;
          prijava.datumPrijave = new Date().toISOString();
        }
      },
      error: (err) => console.error('Greška pri prijavi ispita', err)
    });
  }
}