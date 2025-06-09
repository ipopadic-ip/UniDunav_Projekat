import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GodinaStudijaService } from '../../../../core/services/godina-studija.service';
import { GodinaStudija } from '../../../../core/model/godina-studija.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-godina-studija-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './godina-studija-list.component.html',
  styleUrls: ['./godina-studija-list.component.css']
})
export class GodinaStudijaListComponent implements OnInit {
  private godinaStudijaService = inject(GodinaStudijaService);
  private router = inject(Router);

  godineStudija: GodinaStudija[] = [];

  ngOnInit(): void {
    this.ucitajGodine();
  }


ucitajGodine() {
  this.godinaStudijaService.getAllZaAdmin().subscribe({
    next: (data) => {
      const aktivne = data['aktivne'].sort((a, b) => {
        return a.studijskiProgramNaziv.localeCompare(b.studijskiProgramNaziv);
      });

      const obrisane = data['obrisane'].sort((a, b) => {
        return a.studijskiProgramNaziv.localeCompare(b.studijskiProgramNaziv);
      });

      this.godineStudija = [...aktivne, ...obrisane];
    },
    error: (err) => console.error('Greška pri učitavanju godina studija:', err),
  });
}






  deaktiviraj(id: number) {
    this.godinaStudijaService.deaktivirajGodinu(id).subscribe({
      next: () => this.ucitajGodine(),
      error: (err) => console.error('Greška pri deaktivaciji:', err)
    });
  }

  aktiviraj(id: number) {
    this.godinaStudijaService.aktivirajGodinu(id).subscribe({
      next: () => this.ucitajGodine(),
      error: (err) => console.error('Greška pri aktivaciji:', err)
    });
  }

  dodajGodinu() {
    this.router.navigate(['/admin/godina-studija/add']);
  }

  izmeniGodinu(id: number) {
    this.router.navigate(['/admin/godina-studija/edit', id]);
  }

  goBack() {
    this.router.navigate(['/admin']);
  }
}
