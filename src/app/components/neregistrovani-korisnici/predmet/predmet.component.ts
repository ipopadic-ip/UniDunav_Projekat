import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Predmet } from '../../../core/model/studijski-program.model';

@Component({
  selector: 'app-predmet',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="card mb-3">
      <div class="card-body">
        <h5>{{ predmet.naziv }}</h5>
        <p>
          <a [href]="predmet.silabus" target="_blank">📄 Silabus</a>
        </p>
        <div *ngIf="predmet.materijali?.length">
          <strong>Nastavni materijal:</strong>
          <ul>
            <li *ngFor="let materijal of predmet.materijali">
              <a [href]="materijal" target="_blank">{{ materijal.split('/').pop() }}</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
  `
})
export class PredmetComponent {
  @Input() predmet!: Predmet;
}
