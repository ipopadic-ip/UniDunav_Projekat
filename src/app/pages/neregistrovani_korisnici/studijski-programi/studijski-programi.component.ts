import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
// import { ActivatedRoute } from '@angular/router';
import { RouterModule } from '@angular/router';
import { StudijskiProgramService } from '../../../core/services/studijski-program.service';
import { FakultetService } from '../../../core/services/fakultet.service';
import { StudijskiProgram } from '../../../core/model/studijski-program.model';
import { Fakultet } from '../../../core/model/fakultet.model';
// import { RukovodilacComponent } from '../../../components/neregistrovani-korisnici/rukovodilac/rukovodilac.component';
// import { PredmetComponent } from '../../../components/neregistrovani-korisnici/predmet/predmet.component';

@Component({
  selector: 'app-studijski-programi',
  imports: [CommonModule, RouterModule],
  templateUrl: './studijski-programi.component.html',
  styleUrl: './studijski-programi.component.css'
})
export class StudijskiProgramiComponent implements OnInit {
  programi: StudijskiProgram[] = [];

  fakulteti: Fakultet[] = [];
  grupisaniProgrami: { fakultet: Fakultet, programi: StudijskiProgram[] }[] = [];

  constructor(
    private studijskiProgramService: StudijskiProgramService,
    private fakultetService: FakultetService
  ) {}

  ngOnInit(): void {
    this.fakultetService.getFakulteti().subscribe(fakulteti => {
      this.fakulteti = fakulteti;

      this.studijskiProgramService.getAll().subscribe(programi => {
        this.programi = programi;

        this.grupisaniProgrami = this.fakulteti.map(f => ({
          fakultet: f,
          programi: this.programi.filter(p => p['fakultetId'] === f.id)
        }));
      });
    });
  }

  // constructor(private service: StudijskiProgramService) {}

  // ngOnInit(): void {
  //   this.service.getAll().subscribe((data) => {
  //     this.programi = data;
  //   });
  // }
}
