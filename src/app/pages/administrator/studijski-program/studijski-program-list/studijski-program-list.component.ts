import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { StudijskiProgram } from '../../../../core/model/studijski-program.model';
import { Router } from '@angular/router';
import { StudijskiProgramService } from '../../../../core/services/studijski-program.service';

@Component({
  selector: 'app-studijski-program-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './studijski-program-list.component.html',
  styleUrls: ['./studijski-program-list.component.css']
})
export class StudijskiProgramListComponent implements OnInit {
  private studijskiProgramService = inject(StudijskiProgramService);
  private router = inject(Router);

  studijskiProgrami: any[] = [];

  ngOnInit(): void {
    this.ucitajStudijskePrograme();
  }

  ucitajStudijskePrograme() {
    // this.studijskiProgramService.getAllAdmin().subscribe({
    //   next: (data) => this.studijskiProgrami = data,
     this.studijskiProgramService.getAllAdmin().subscribe({
    next: (data) => {
        this.studijskiProgrami = data.sort((a, b) => {
        // Prvo sortiraj po "deleted": false ispred true
        if (a.deleted !== b.deleted) {
          return a.deleted ? 1 : -1;
        }

        // Ako su oba deleted ili oba nisu, poredi po katedraNaziv
        const katedraA = a.katedraNaziv?.toLowerCase() || '';
        const katedraB = b.katedraNaziv?.toLowerCase() || '';
        return katedraA.localeCompare(katedraB);
      });

    },
      error: (err) => console.error('Greška pri učitavanju programa:', err)
    });
  }

  deaktivirajStudijskiProgram(id: number) {
    this.studijskiProgramService.deaktiviraj(id).subscribe({
      next: () => this.ucitajStudijskePrograme(),
      error: (err) => console.error('Greška pri deaktivaciji:', err)
    });
  }

  aktivirajStudijskiProgram(id: number) {
    this.studijskiProgramService.aktiviraj(id).subscribe({
      next: () => this.ucitajStudijskePrograme(),
      error: (err) => console.error('Greška pri aktivaciji:', err)
    });
  }

  dodajStudijskiProgram() {
    this.router.navigate(['/admin/studijski-program/add']);
  }

  izmeniStudijskiProgram(id: number) {
    this.router.navigate(['/admin/studijski-program/edit', id]);
  }

  goBack() {
    this.router.navigate(['/admin']);
  }
}
