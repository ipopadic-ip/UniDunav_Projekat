import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-footer',
  imports: [CommonModule],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent {
  @Input() nazivUniverziteta: string = '';
  @Input() lokacija: string = '';
  @Input() telefon: string = '';
  @Input() email: string = '';
}
