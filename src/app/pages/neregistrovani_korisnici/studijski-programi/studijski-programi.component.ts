import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
// import { ActivatedRoute } from '@angular/router';
import { RouterModule } from '@angular/router';
import { StudijskiProgramService } from '../../../core/services/studijski-program.service';
import { StudijskiProgram } from '../../../core/model/studijski-program.model';
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

  constructor(private service: StudijskiProgramService) {}

  ngOnInit(): void {
    this.service.getAll().subscribe((data) => {
      this.programi = data;
    });
  }
}
