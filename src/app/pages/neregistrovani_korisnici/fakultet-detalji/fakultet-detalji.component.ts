import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ContactComponent } from '../../../components/neregistrovani-korisnici/shared/contact/contact.component';
import { RectorComponent } from '../../../components/neregistrovani-korisnici/shared/rector/rector.component';
import { FakultetService } from '../../../core/services/fakultet.service';
import { Fakultet } from '../../../core/model/fakultet.model';
import { AboutUsComponent } from '../../../components/neregistrovani-korisnici/shared/about-us/about-us.component';


@Component({
  selector: 'app-fakultet-detalji',
  standalone: true,
  imports: [CommonModule, ContactComponent, RectorComponent, AboutUsComponent],
  templateUrl: './fakultet-detalji.component.html',
  styleUrl: './fakultet-detalji.component.css'
})
export class FakultetDetaljiComponent {
  fakultetId!: number;
  fakultetData?: Fakultet;

  constructor(
    private route: ActivatedRoute,
    private fakultetService: FakultetService
  ) {
    this.route.paramMap.subscribe(params => {
      this.fakultetId = Number(params.get('id'));
      this.fakultetService.getFakultetById(this.fakultetId).subscribe(fakultet => {
        this.fakultetData = fakultet;
      });
    });
  }

  get kontaktData() {
    if (!this.fakultetData) return null;
    return {
      email: this.fakultetData.kontakt,
      telefon: this.fakultetData.telefon,
      lokacija: this.fakultetData.lokacija
    };
  }

  get aboutData() {
    if (!this.fakultetData) return null;
    return {
      title: this.fakultetData.naziv,
      description: this.fakultetData.opis,
      image: this.fakultetData.slika
    };
  }

  get dekanData() {
    if (!this.fakultetData) return null;
    return {
      ime: this.fakultetData.dekan.ime,
      titula: this.fakultetData.dekan.titula,
      opis1: this.fakultetData.dekan.opis,
      slikaSrc: this.fakultetData.dekan.slika,
      altText: this.fakultetData.dekan.ime
    };
  }
}

