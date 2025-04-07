import { Component, Input, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
// import { Univerzitet } from '../../../../core/model/univerzitet.model';
// import { UniverzitetService } from '../../../../core/services/univerzitet.service';

@Component({
  selector: 'app-about-us',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './about-us.component.html',
  styleUrl: './about-us.component.css'
})
export class AboutUsComponent {
  @Input() title?: string;
  @Input() description?: string;
  @Input() image?: string;

  // univerzitetService = inject(UniverzitetService);
  // univerzitet: Univerzitet | null = null;

  // ngOnInit(): void {
  //   if (!this.title || !this.description || !this.image) {
  //     this.univerzitetService.getUniverzitet().subscribe((data) => {
  //       this.univerzitet = data;
  //     });
  //   }
  // }
}

