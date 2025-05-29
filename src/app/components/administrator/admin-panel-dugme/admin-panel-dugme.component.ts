import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-admin-panel-dugme',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './admin-panel-dugme.component.html',
  styleUrl: './admin-panel-dugme.component.css'
})
export class AdminPanelDugmeComponent {
  @Input() label: string = '';
  @Input() route: string = '';
}
