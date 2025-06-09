import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { KatedraService } from '../../../../core/services/katedra.service';
import { Katedra } from '../../../../core/model/katedra.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-katedra-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './katedra-list.component.html',
  styleUrls: ['./katedra-list.component.css']
})
export class KatedraListComponent implements OnInit {
  private katedraService = inject(KatedraService);
  private router = inject(Router);

  katedre: Katedra[] = [];

  ngOnInit(): void {
    this.ucitajKatedre();
  }

  ucitajKatedre() {
    this.katedraService.getAllKatedreAdmin().subscribe({
      next: (data) => this.katedre = data,
      error: (err) => console.error('Greška pri učitavanju katedri:', err)
    });
  }

  deaktivirajKatedru(id: number) {
    this.katedraService.deaktivirajKatedru(id).subscribe({
      next: () => this.ucitajKatedre(),
      error: (err) => console.error('Greška pri deaktivaciji:', err)
    });
  }

  aktivirajKatedru(id: number) {
    this.katedraService.aktivirajKatedru(id).subscribe({
      next: () => this.ucitajKatedre(),
      error: (err) => console.error('Greška pri aktivaciji:', err)
    });
  }

  dodajKatedru() {
    this.router.navigate(['/admin/katedra/add']);
  }

  izmeniKatedru(id: number) {
    this.router.navigate(['/admin/katedra/edit', id]);
  }

  goBack() {
    this.router.navigate(['/admin']);
  }
}
