import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, ParamMap, RouterModule } from '@angular/router';
import { KatedraService } from '../../../core/services/katedra.service';
import { Katedra } from '../../../core/model/katedra.model';
import { RectorComponent } from '../../../components/neregistrovani-korisnici/shared/rector/rector.component';
import { StudijskiProgram } from '../../../core/model/studijski-program.model';
import { StudijskiProgramService } from '../../../core/services/studijski-program.service';

@Component({
  selector: 'app-katedra-detalji',
  standalone: true,
  imports: [CommonModule, RectorComponent, RouterModule],
  templateUrl: './katedra-detalji.component.html',
  styleUrl: './katedra-detalji.component.css'
})
export class KatedraDetaljiComponent implements OnInit {
  katedra?: Katedra;
  studijskiProgramiPoTipu: Record<string, StudijskiProgram[]> = {};

  constructor(
    private route: ActivatedRoute,
    private katedraService: KatedraService,
    private studijskiProgramService: StudijskiProgramService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe((params: ParamMap) => {
      const id = Number(params.get('id'));
      if (!id) return;

      this.katedraService.getKatedraById(id).subscribe(k => {
        this.katedra = k;
      });

      this.studijskiProgramService.getStudijskiProgramiGroupedByTip(id).subscribe(data => {
        this.studijskiProgramiPoTipu = data;
      });
    });
  }

  // constructor(private route: ActivatedRoute,
  //   private katedraService: KatedraService,
  //   private studijskiProgramService: StudijskiProgramService) {

  //   const id = Number(this.route.snapshot.paramMap.get('id'));
  //   this.katedraService.getKatedraById(id).subscribe(k => {
  //     this.katedra = k;
  //   });

  //    this.studijskiProgramService.getStudijskiProgramiGroupedByTip(id).subscribe(data => {
  //     this.studijskiProgramiPoTipu = data;
  //   });    
  // }

  get sefKatedreData() {
    if (!this.katedra || !this.katedra.sefKatedre) return null;
    const sef = this.katedra.sefKatedre;
    return {
      ime: `${sef.ime} ${sef.prezime}`,
      titula: 'Šef katedre',
      opis1: sef.biografija,
      slikaSrc: `http://localhost:8080/${sef.slikaPath}`,
      altText: `${sef.ime} ${sef.prezime}`
    };
  }
}
