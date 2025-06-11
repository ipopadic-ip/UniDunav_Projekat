import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DokumentService } from '../../../../core/services/dokument.service';
import { Dokument } from '../../../../core/model/dokument.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dokument-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dokument-list.component.html',
  styleUrls: ['./dokument-list.component.css']
})
export class DokumentListComponent implements OnInit {
  private dokumentService = inject(DokumentService);
  private router = inject(Router);

  dokumenti: Dokument[] = [];

  ngOnInit(): void {
    this.ucitajDokumente();
  }

 ucitajDokumente() {
  this.dokumentService.getAllDokumentiAdmin().subscribe({
    next: (data) => {
      const aktivni = data
        .filter(d => !d.deleted)
        .sort((a, b) => a.naziv.localeCompare(b.naziv));

      const obrisani = data
        .filter(d => d.deleted)
        .sort((a, b) => a.naziv.localeCompare(b.naziv));

      this.dokumenti = [...aktivni, ...obrisani];
    },
    error: (err) => console.error('Greška pri učitavanju dokumenata:', err)
  });
}


  deaktiviraj(id: number) {
    this.dokumentService.deaktiviraj(id).subscribe({
      next: () => this.ucitajDokumente(),
      error: (err) => console.error('Greška pri deaktivaciji dokumenta:', err)
    });
  }

  aktiviraj(id: number) {
    this.dokumentService.aktiviraj(id).subscribe({
      next: () => this.ucitajDokumente(),
      error: (err) => console.error('Greška pri aktivaciji dokumenta:', err)
    });
  }

  izmeni(id: number) {
    this.router.navigate(['/sluzbenik/dokument/edit', id]);
  }

  dodaj() {
    this.router.navigate(['/sluzbenik/dokument/add']);
  }

  goBack() {
    this.router.navigate(['/sluzbenik']);
  }
}
