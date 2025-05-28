import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, ParamMap, RouterModule } from '@angular/router';
import { StudijskiProgram } from '../../../core/model/studijski-program.model';
import { StudijskiProgramService } from '../../../core/services/studijski-program.service';
import { RectorComponent } from '../../../components/neregistrovani-korisnici/shared/rector/rector.component';

@Component({
  selector: 'app-studijski-program-detalji',
  standalone: true,
  imports: [CommonModule, RouterModule, RectorComponent],
  templateUrl: './studijski-program-detalji.component.html',
  styleUrl: './studijski-program-detalji.component.css'
})
export class StudijskiProgramDetaljiComponent implements OnInit {
  studijskiProgram?: StudijskiProgram;
  predmetiPoGodinama: Record<number, { id: number; naziv: string; ects: number }[]> = {};


  constructor(
    private route: ActivatedRoute,
    private studijskiProgramService: StudijskiProgramService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      const id = Number(params.get('id'));
      if (!id) return;

      this.studijskiProgramService.getStudijskiProgramById(id).subscribe(p => {
        this.studijskiProgram = p;
      });

       this.studijskiProgramService.getPredmetiPoGodinama(id).subscribe(data => {
        this.predmetiPoGodinama = data;
      });
    });
  }

  get rukovodilacData() {
    if (!this.studijskiProgram || !this.studijskiProgram.rukovodilac) return null;
    const r = this.studijskiProgram.rukovodilac;
    return {
      ime: `${r.ime} ${r.prezime}`,
      titula: 'Rukovodilac programa',
      opis1: r.biografija,
      slikaSrc: `http://localhost:8080/${r.slikaPath}`,
      altText: `${r.ime} ${r.prezime}`
    };
  }
}
