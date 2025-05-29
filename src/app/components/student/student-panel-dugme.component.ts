import { Component, Input } from '@angular/core';
import { RouterModule } from '@angular/router'; // dodaj ovo

@Component({
  selector: 'app-student-panel-dugme',
  standalone: true,
  imports: [RouterModule], // dodaj ovo
  templateUrl: './student-panel-dugme.component.html',
  styleUrls: ['./student-panel-dugme.component.css']
})
export class StudentPanelDugmeComponent {
  @Input() label: string = '';
  @Input() route: string = '';
}