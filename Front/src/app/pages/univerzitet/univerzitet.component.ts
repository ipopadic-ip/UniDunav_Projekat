import { Component } from '@angular/core';
import { HeroComponent } from '../../components/hero/hero.component';
import { WhyChooseUsComponent } from '../../components/why-choose-us/why-choose-us.component';
import { AboutUsComponent } from '../../components/about-us/about-us.component';
import { RectorComponent } from '../../components/rector/rector.component';
import { LocationComponent } from '../../components/location/location.component';
import { ContactComponent } from '../../components/contact/contact.component';
import { FooterComponent } from '../../components/footer/footer.component';

@Component({
  selector: 'app-univerzitet',
  imports: [HeroComponent, WhyChooseUsComponent, AboutUsComponent, RectorComponent, LocationComponent, ContactComponent, FooterComponent],
  templateUrl: './univerzitet.component.html',
  styleUrl: './univerzitet.component.css'
})
export class UniverzitetComponent {

}
