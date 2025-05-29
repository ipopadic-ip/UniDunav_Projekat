import { Component, OnInit } from '@angular/core';
import { Predmet } from '../../core/model/predmet.model';
import { CommonModule } from '@angular/common'; // ⬅️ dodaj ovo
import { PredmetService } from '../../core/services/predmet.service';
import { AuthService } from '../../core/services/auth.service';

@Component({
  selector: 'app-pregled-predmeta',
    standalone: true,
     imports: [CommonModule], // ⬅️ OVO JE KLJUČNO
  templateUrl: './pregled-predmeta.component.html',
  styleUrls: ['./pregled-predmeta.component.css']
})
export class PregledPredmetaComponent implements OnInit {
  predmeti: Predmet[] = [];
  studentId!: number;
  
  constructor(
    private predmetService: PredmetService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
  this.studentId = this.authService.getLoggedInUserId();
  console.log("Student ID:", this.studentId); // Proveri da li je ID dobar

  this.predmetService.getPredmetiZaStudenta(this.studentId).subscribe({
    next: (data) => {
      console.log("Dobijeni predmeti:", data); // Ovde vidiš da li API vraća nešto
      this.predmeti = data;
    },
    error: (err) => console.error('Greška prilikom učitavanja predmeta', err)
  });
}
}