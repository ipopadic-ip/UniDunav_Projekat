import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { StudijskiProgramService } from '../../../core/services/studijski-program.service';
import { StudijskiProgram } from '../../../core/model/studijski-program.model';
import { RukovodilacComponent } from '../../../components/neregistrovani-korisnici/rukovodilac/rukovodilac.component';
import { PredmetComponent } from '../../../components/neregistrovani-korisnici/predmet/predmet.component';

@Component({
  selector: 'app-studijski-program-detalji',
  standalone: true,
  imports: [CommonModule, RukovodilacComponent, PredmetComponent],
  template: `
    <div *ngIf="program" class="container py-5 my-5">
      <h2 class="mb-3">{{ program.naziv }}</h2>
      <p>{{ program.opis }}</p>

      <hr />
      <h4>Rukovodilac smera</h4>
      <app-rukovodilac [rukovodilac]="program.rukovodilac"></app-rukovodilac>

      <hr />
      <h4>Predmeti</h4>
      <app-predmet *ngFor="let p of program.predmeti" [predmet]="p"></app-predmet>
    </div>
  `,
})
export class StudijskiProgramDetaljiComponent implements OnInit {
  program!: StudijskiProgram;

  constructor(
    private route: ActivatedRoute,
    private service: StudijskiProgramService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.service.getById(id).subscribe((data) => {
      this.program = data;
    });
  }
}
