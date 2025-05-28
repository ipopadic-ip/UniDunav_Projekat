import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { DepartmanService } from '../../../core/services/departman.service';
import { Departman } from '../../../core/model/departman.model';

import { KatedraService } from '../../../core/services/katedra.service';
import { Katedra } from '../../../core/model/katedra.model';
import { OsnovneKarticeComponent } from '../../../components/neregistrovani-korisnici/osnovne-kartice/osnovne-kartice.component';

import { RectorComponent } from '../../../components/neregistrovani-korisnici/shared/rector/rector.component';
@Component({
  selector: 'app-departman-detalji',
  standalone: true,
  imports: [CommonModule, RectorComponent, OsnovneKarticeComponent],
  templateUrl: './departman-detalji.component.html',
  styleUrl: './departman-detalji.component.css'
})
export class DepartmanDetaljiComponent {
  departmanId!: number;
  departman?: Departman;
  katedre: Katedra[] = [];

  constructor(
    private route: ActivatedRoute,
    private departmanService: DepartmanService,
    private katedraService: KatedraService
  ) {
    this.route.paramMap.subscribe(params => {
      this.departmanId = Number(params.get('id'));
      this.departmanService.getDepartmanById(this.departmanId).subscribe(dep => {
        this.departman = dep;
      });
       this.katedraService.getKatedreByDepartmanId(this.departmanId).subscribe(data => {
        this.katedre = data;
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
