// import { Component } from '@angular/core';
// import { HeroComponent } from '../../../components/neregistrovani-korisnici/hero/hero.component';
// import { WhyChooseUsComponent } from '../../../components/neregistrovani-korisnici/why-choose-us/why-choose-us.component';
// import { AboutUsComponent } from '../../../components/neregistrovani-korisnici/shared/about-us/about-us.component';
// import { RectorComponent } from '../../../components/neregistrovani-korisnici/shared/rector/rector.component';
// import { LocationComponent } from '../../../components/neregistrovani-korisnici/shared/location/location.component';
// import { ContactComponent } from '../../../components/neregistrovani-korisnici/shared/contact/contact.component';


// @Component({
//   selector: 'app-univerzitet',
//   imports: [HeroComponent, WhyChooseUsComponent, AboutUsComponent, RectorComponent, LocationComponent, ContactComponent],
//   templateUrl: './univerzitet.component.html',
//   styleUrl: './univerzitet.component.css'
// })
// export class UniverzitetComponent {

// }

import { Component } from '@angular/core';
import { HeroComponent } from '../../../components/neregistrovani-korisnici/hero/hero.component';
import { WhyChooseUsComponent } from '../../../components/neregistrovani-korisnici/why-choose-us/why-choose-us.component';
import { AboutUsComponent } from '../../../components/neregistrovani-korisnici/shared/about-us/about-us.component';
import { RectorComponent } from '../../../components/neregistrovani-korisnici/shared/rector/rector.component';
import { LocationComponent } from '../../../components/neregistrovani-korisnici/shared/location/location.component';
import { ContactComponent } from '../../../components/neregistrovani-korisnici/shared/contact/contact.component';

@Component({
  selector: 'app-univerzitet',
  standalone: true,
  imports: [HeroComponent, WhyChooseUsComponent, AboutUsComponent, RectorComponent, LocationComponent, ContactComponent],
  templateUrl: './univerzitet.component.html',
  styleUrl: './univerzitet.component.css'
})
export class UniverzitetComponent {
  aboutData = {
    title: 'O Univerzitetu Dunav',
    description: 'Doktor',
    image: 'assets/images/poz1.jpeg'
  };

  kontaktData = {
    email: 'kontakt@unidunav.edu.rs',
    telefon: '+381 64 123 4567',
    lokacija: 'Heroja Pinkija 88, Srbija'
  };
}
