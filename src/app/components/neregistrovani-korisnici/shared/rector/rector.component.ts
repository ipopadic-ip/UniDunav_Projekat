import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-rector',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './rector.component.html',
  styleUrl: './rector.component.css'
})
export class RectorComponent {
  @Input() ime!: string;
  @Input() titula!: string;
  @Input() opis1!: string;
  @Input() opis2?: string;
  @Input() opis3?: string;
  @Input() slikaSrc: string = 'assets/images/default.jpg';
  @Input() altText: string = 'Profilna slika';
}
