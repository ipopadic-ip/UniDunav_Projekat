import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Fakultet } from '../../../../core/model/fakultet.model';
import { FakultetService } from '../../../../core/services/fakultet.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-fakultet-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './fakultet-list.component.html',
  styleUrls: ['./fakultet-list.component.css']
})
export class FakultetListComponent implements OnInit {
  private fakultetService = inject(FakultetService);
  private router = inject(Router);

  fakulteti: Fakultet[] = [];

  ngOnInit(): void {
    this.fakultetService.getFakultetiAdmin().subscribe({
      next: (data) => this.fakulteti = data,
      error: (err) => console.error('Greška pri učitavanju fakulteta:', err)
    });
  }

  aktivirajFakultet(id: number): void {
    this.fakultetService.activateFakultet(id).subscribe({
      next: () => {
        const fakultet = this.fakulteti.find(f => f.id === id);
        if (fakultet) fakultet.deleted = false;
      },
      error: (err) => console.error('Greška pri aktivaciji fakulteta:', err)
    });
  }

  deaktivirajFakultet(id: number): void {
    this.fakultetService.deactivateFakultet(id).subscribe({
      next: () => {
        const fakultet = this.fakulteti.find(f => f.id === id);
        if (fakultet) fakultet.deleted = true;
      },
      error: (err) => console.error('Greška pri deaktivaciji fakulteta:', err)
    });
  }


  goBack(): void {
    this.router.navigate(['/admin']);
  }

  dodajFakultet() {
    this.router.navigate(['/admin/fakultet/add']);
  }

  izmeniFakultet(id: number) {
    this.router.navigate(['/admin/fakultet/edit', id]);
  }
}
