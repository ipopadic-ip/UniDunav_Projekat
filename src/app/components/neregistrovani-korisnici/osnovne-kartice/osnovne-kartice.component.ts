import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Katedra } from '../../../core/model/katedra.model';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-osnovne-kartice',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './osnovne-kartice.component.html',
  styleUrls: ['./osnovne-kartice.component.css']
})
export class OsnovneKarticeComponent {
  @Input() katedre: Katedra[] = [];
}
