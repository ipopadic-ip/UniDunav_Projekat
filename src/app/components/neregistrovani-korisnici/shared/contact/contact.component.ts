import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
// import { CommonModule } from '@angular/common';

interface Kontakt {
  email: string;
  telefon: string;
  lokacija:string;
}

@Component({
  selector: 'app-contact',
  imports: [CommonModule],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.css'
})
export class ContactComponent {
  @Input() kontakt!: Kontakt;
}
