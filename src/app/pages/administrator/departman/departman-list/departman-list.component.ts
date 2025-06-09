// departman-list.component.ts
import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DepartmanService } from '../../../../core/services/departman.service';
import { Departman } from '../../../../core/model/departman.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-departman-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './departman-list.component.html',
  styleUrls: ['./departman-list.component.css']
})
export class DepartmanListComponent implements OnInit {
  private departmanService = inject(DepartmanService);
  private router = inject(Router);

  departmani: Departman[] = [];

  ngOnInit(): void {
    this.ucitajDepartmane();
    // this.departmanService.getAllDepartmaniAdmin().subscribe({
    //   next: (data) => this.departmani = data,
    //   error: (err) => console.error('Greška pri učitavanju departmana:', err)
    // });
  }

  ucitajDepartmane() {
    this.departmanService.getAllDepartmaniAdmin().subscribe({
      next: (data) => this.departmani = data,
      error: (err) => console.error('Greška pri učitavanju departmana:', err)
    });
  }

  deaktivirajDepartman(id: number) {
    this.departmanService.deaktivirajDepartman(id).subscribe({
      next: () => this.ucitajDepartmane(),
      error: (err) => console.error('Greška pri deaktivaciji:', err)
    });
  }

  aktivirajDepartman(id: number) {
    this.departmanService.aktivirajDepartman(id).subscribe({
      next: () => this.ucitajDepartmane(),
      error: (err) => console.error('Greška pri aktivaciji:', err)
    });
  }

  dodajDepartman() {
    this.router.navigate(['/admin/departman/add']);
  }

  izmeniDepartman(id: number) {
    this.router.navigate(['/admin/departman/edit', id]);
  }

  goBack() {
    this.router.navigate(['/admin']);
  }
}
