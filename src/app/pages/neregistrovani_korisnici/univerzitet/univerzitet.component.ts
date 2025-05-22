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


  get aboutData() {
  if (!this.univerzitetData) return undefined;
  return {
    title: this.univerzitetData.naziv,
    description: this.univerzitetData.opis,
    image: `http://localhost:8080/${this.univerzitetData.slika1Path}`
    // image: this.univerzitetData.slika2Path,
  };
}

get kontaktData() {
  if (!this.univerzitetData) return null;
  return {
    email: this.univerzitetData.email,
    telefon: this.univerzitetData.brojTelefona,
    lokacija: this.univerzitetData.lokacija,
  };
}

get heroData() {
  if (!this.univerzitetData) return undefined;
  return {
    title: this.univerzitetData.naziv,
    subtitle: this.univerzitetData.podnaslov,
    image: `http://localhost:8080/${this.univerzitetData.slika2Path}` // napravi punu URL putanju
    // image: this.univerzitetData.slika1Path 
  };
}


  
  

  // Pristup podacima o rektoru
 get rektorData() {
  if (!this.univerzitetData || !this.univerzitetData.rektor) return undefined;
  return {
    ime: `${this.univerzitetData.rektor.ime} ${this.univerzitetData.rektor.prezime}`,
    titula: 'Rektor Univerziteta', // Ako želiš dinamičku titulu, dodaj je u backend i model
    slikaSrc: `http://localhost:8080/${this.univerzitetData.rektor.slikaPath}`,
    altText: 'Slika rektora',
    opis1: this.univerzitetData.rektor.biografija,
  };
}

}