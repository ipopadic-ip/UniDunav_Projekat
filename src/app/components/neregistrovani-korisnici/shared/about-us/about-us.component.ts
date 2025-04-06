// import { Component } from '@angular/core';
// import { CommonModule } from '@angular/common';

// @Component({
//   selector: 'app-about-us',
//   imports: [CommonModule],
//   templateUrl: './about-us.component.html',
//   styleUrl: './about-us.component.css'
// })
// export class AboutUsComponent {

// }

import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';

interface AboutUsData {
  title: string;
  description: string;
  image: string;
}

@Component({
  selector: 'app-about-us',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './about-us.component.html',
  styleUrl: './about-us.component.css'
})
export class AboutUsComponent {
  @Input() data!: AboutUsData;
}

