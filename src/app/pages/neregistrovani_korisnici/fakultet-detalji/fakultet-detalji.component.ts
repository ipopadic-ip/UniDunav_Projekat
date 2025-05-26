import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ContactComponent } from '../../../components/neregistrovani-korisnici/shared/contact/contact.component';
import { RectorComponent } from '../../../components/neregistrovani-korisnici/shared/rector/rector.component';
import { FakultetService } from '../../../core/services/fakultet.service';
import { Fakultet } from '../../../core/model/fakultet.model';
import { AboutUsComponent } from '../../../components/neregistrovani-korisnici/shared/about-us/about-us.component';
import { RouterModule } from '@angular/router';

import { DepartmanService } from '../../../core/services/departman.service';
import { Departman } from '../../../core/model/departman.model';

@Component({
  selector: 'app-fakultet-detalji',
  standalone: true,
  imports: [CommonModule, ContactComponent, RectorComponent, AboutUsComponent, RouterModule],
  templateUrl: './fakultet-detalji.component.html',
  styleUrl: './fakultet-detalji.component.css'
})
export class FakultetDetaljiComponent {
  fakultetId!: number;
  fakultetData?: Fakultet;
  departmani: Departman[] = [];

  // constructor(
  //   private route: ActivatedRoute,
  //   private fakultetService: FakultetService
  // ) {
  //   this.route.paramMap.subscribe(params => {
  //     this.fakultetId = Number(params.get('id'));
  //     this.fakultetService.getFakultetById(this.fakultetId).subscribe(fakultet => {
  //       this.fakultetData = fakultet;
  //     });
  //   });
  // }

  constructor(
    private route: ActivatedRoute,
    private fakultetService: FakultetService,
    private departmanService: DepartmanService
  ) {
    this.route.paramMap.subscribe(params => {
      this.fakultetId = Number(params.get('id'));

      this.fakultetService.getFakultetById(this.fakultetId).subscribe(fakultet => {
        this.fakultetData = fakultet;
      });

      this.departmanService.getDepartmaniByFakultetId(this.fakultetId).subscribe(data => {
        this.departmani = data;
      });
    });
  }

  get kontaktData() {
    if (!this.fakultetData) return null;
    return {
      email: this.fakultetData.email,
      telefon: this.fakultetData.brojTelefona,
      lokacija: this.fakultetData.lokacija
    };
  }

  get aboutData() {
    if (!this.fakultetData) return null;
    return {
      title: this.fakultetData.naziv,
      description: this.fakultetData.opis,
      image: null
      // image: '' 
    };
  }

  get dekanData() {
    if (!this.fakultetData) return null;
    return {
      ime: `${this.fakultetData.dekanIme} ${this.fakultetData.dekanPrezime}`,
      titula: 'Dekan Fakulteta',
      opis1: this.fakultetData.dekanOpis,
      slikaSrc: `http://localhost:8080/${this.fakultetData.dekanSlika}`,

      altText: `${this.fakultetData.dekanIme} ${this.fakultetData.dekanPrezime}`
    };
  }
}
