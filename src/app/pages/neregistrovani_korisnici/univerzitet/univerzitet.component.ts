import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // ⬅️ dodaj ovo
import { HeroComponent } from '../../../components/neregistrovani-korisnici/hero/hero.component';
import { WhyChooseUsComponent } from '../../../components/neregistrovani-korisnici/why-choose-us/why-choose-us.component';
import { AboutUsComponent } from '../../../components/neregistrovani-korisnici/shared/about-us/about-us.component';
import { RectorComponent } from '../../../components/neregistrovani-korisnici/shared/rector/rector.component';
// import { LocationComponent } from '../../../components/neregistrovani-korisnici/shared/location/location.component';
import { ContactComponent } from '../../../components/neregistrovani-korisnici/shared/contact/contact.component';
import { Univerzitet } from '../../../core/model/univerzitet.model';
import { UniverzitetService } from '../../../core/services/univerzitet.service';


@Component({
  selector: 'app-univerzitet',
  standalone: true,
  imports: [HeroComponent, WhyChooseUsComponent, CommonModule, AboutUsComponent, RectorComponent, ContactComponent],
  templateUrl: './univerzitet.component.html',
  styleUrl: './univerzitet.component.css'
})
export class UniverzitetComponent implements OnInit {
  univerzitetData?: Univerzitet; // Čuvanje podataka o univerzitetu

  constructor(private univerzitetService: UniverzitetService) {}

  ngOnInit(): void {
    // Učitavanje podataka iz servisa
    this.univerzitetService.getUniverzitet().subscribe((univerzitet) => {
      this.univerzitetData = univerzitet; // Dodeljivanje podataka univerziteta
    });
  }

  // Pristup podacima o kontaktu
  get kontaktData(): { email: string; telefon: string; lokacija: string } | null {
    if (!this.univerzitetData) return null;
    return {
      email: this.univerzitetData.kontakt,
      telefon: this.univerzitetData.telefon,
      lokacija: this.univerzitetData.lokacija,
    };
  }
  
  

  // Pristup podacima o "About Us"
  get aboutData() {
    if (!this.univerzitetData) return undefined;
    return {
      title: this.univerzitetData.naziv,
      description: this.univerzitetData.opis,
      image: this.univerzitetData.slika,
    };
  }

  // Pristup podacima o rektoru
  get rektorData() {
    if (!this.univerzitetData) return undefined;
    return {
      ime: this.univerzitetData.rektor.ime,
      titula: this.univerzitetData.rektor.titula,
      slikaSrc: this.univerzitetData.rektor.slika,
      altText: this.univerzitetData.rektor.ime,
      opis1: this.univerzitetData.rektor.opis,
    };
  }
}