import { Component, Input, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';

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

}

