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
  @Input() slikaSrc?: string;
  @Input() altText?: string;
}
