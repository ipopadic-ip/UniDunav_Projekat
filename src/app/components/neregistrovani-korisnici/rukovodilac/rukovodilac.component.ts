import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Rukovodilac } from '../../../core/model/studijski-program.model';

@Component({
  selector: 'app-rukovodilac',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="row my-4">
      <div class="col-md-4 text-center">
        <img [src]="rukovodilac.slika" class="img-fluid rounded" [alt]="rukovodilac.ime" />
      </div>
      <div class="col-md-8">
        <h4>{{ rukovodilac.ime }}</h4>
        <h6 class="text-muted">{{ rukovodilac.titula }}</h6>
        <p>{{ rukovodilac.opis }}</p>
      </div>
    </div>
  `,
})
export class RukovodilacComponent {
  @Input() rukovodilac!: Rukovodilac;
}
