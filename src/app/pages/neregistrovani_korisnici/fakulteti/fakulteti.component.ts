import { Component, OnInit  } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FakultetService } from '../../../core/services/fakultet.service';
import { Fakultet } from '../../../core/model/fakultet.model';
import { FakultetCardComponent } from '../../../components/neregistrovani-korisnici/fakultet-card/fakultet-card.component';

@Component({
  selector: 'app-fakulteti',
  imports: [CommonModule, RouterModule, FakultetCardComponent],
  templateUrl: './fakulteti.component.html',
  styleUrl: './fakulteti.component.css'
})
export class FakultetiComponent implements OnInit {
  fakulteti: Fakultet[] = [];

  constructor(private fakultetService: FakultetService) {}

  ngOnInit(): void {
    this.fakultetService.getFakulteti().subscribe(data => {
      this.fakulteti = data;
    });
  }
}
