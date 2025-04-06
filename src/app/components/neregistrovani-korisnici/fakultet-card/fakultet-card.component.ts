import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

interface Fakultet {
  id: number;
  naziv: string;
  opis: string;
  slika: string;
}

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
