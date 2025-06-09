import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProfesorPredmetService } from '../../../../core/services/profesor-predmet.service';
import { ProfesorPredmet } from '../../../../core/model/profesor-predmet.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profesor-predmet-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './profesor-predmet-list.component.html',
  styleUrls: ['./profesor-predmet-list.component.css']
})
export class ProfesorPredmetListComponent implements OnInit {
  private service = inject(ProfesorPredmetService);
  private router = inject(Router);

  veza: ProfesorPredmet[] = [];

  ngOnInit(): void {
    this.ucitaj();
  }

  ucitaj() {
    this.service.getAllAdmin().subscribe({
      next: (data) => {
        this.veza = data.sort((a, b) => {
          if (a.predmetNaziv === b.predmetNaziv) {
            return a.profesorIme.localeCompare(b.profesorIme);
          }
          return a.predmetNaziv.localeCompare(b.predmetNaziv);
        });
      },
      error: (err) => console.error('Greška pri učitavanju:', err)
    });
  }

  deaktiviraj(id: number) {
    this.service.deaktiviraj(id).subscribe({
      next: () => this.ucitaj(),
      error: (err) => console.error('Greška pri deaktivaciji:', err)
    });
  }

  aktiviraj(id: number) {
    this.service.aktiviraj(id).subscribe({
      next: () => this.ucitaj(),
      error: (err) => console.error('Greška pri aktivaciji:', err)
    });
  }

   dodajProfesorPredmet() {
    this.router.navigate(['/admin/profesor-predmet/add']);
  }

  izmeniProfesorPredmet(id: number) {
    this.router.navigate(['/admin/profesor-predmet/edit', id]);
  }

  goBack() {
    this.router.navigate(['/admin']);
  }
}
