import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { DepartmanService } from '../../../core/services/departman.service';
import { Departman } from '../../../core/model/departman.model';

import { RectorComponent } from '../../../components/neregistrovani-korisnici/shared/rector/rector.component';
@Component({
  selector: 'app-departman-detalji',
  standalone: true,
  imports: [CommonModule, RectorComponent],
  templateUrl: './departman-detalji.component.html',
  styleUrl: './departman-detalji.component.css'
})
export class DepartmanDetaljiComponent {
  departmanId!: number;
  departman?: Departman;

  constructor(
    private route: ActivatedRoute,
    private departmanService: DepartmanService
  ) {
    this.route.paramMap.subscribe(params => {
      this.departmanId = Number(params.get('id'));
      this.departmanService.getDepartmanById(this.departmanId).subscribe(dep => {
        this.departman = dep;
      });
    });
  }

   get sefDepartmanaData() {
    if (!this.departman || !this.departman.sefDepartmana) return null;

    const sef = this.departman.sefDepartmana;

    return {
      ime: `${sef.ime} ${sef.prezime}`,
      titula: 'Šef departmana',
      opis1: sef.biografija,
      slikaSrc: `http://localhost:8080/${sef.slikaPath}`,
      altText: `${sef.ime} ${sef.prezime}`
    };
  }
}
