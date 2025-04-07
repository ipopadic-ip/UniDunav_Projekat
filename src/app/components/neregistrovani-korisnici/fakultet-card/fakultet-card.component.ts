import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { Fakultet } from '../../../core/model/fakultet.model';

@Component({
  selector: 'app-fakultet-card',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './fakultet-card.component.html',
  styleUrls: ['./fakultet-card.component.css']
})
export class FakultetCardComponent {
  @Input() fakultet!: Fakultet;
}
