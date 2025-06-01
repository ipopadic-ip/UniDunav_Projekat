import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { StudentService } from '../../core/services/student.service';
import { AuthService } from '../../core/services/auth.service';

@Component({
  standalone: true,
  selector: 'app-istorija-studiranja',
  imports: [CommonModule],
  templateUrl: './istorija-studiranja.component.html',
  styleUrls: ['./istorija-studiranja.component.css']
})
export class IstorijaStudiranjaComponent implements OnInit {
  predmeti: any[] = [];
   
  prosecnaOcena: number = 0;
  ukupnoECTS: number = 0;

  constructor(
    private studentService: StudentService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    const studentId = this.authService.getLoggedInUserId(); // ili token decode
    this.studentService.getIstorijaStudiranja(studentId).subscribe({
      next: (data) => {
        this.predmeti = data.predmeti;
        this.prosecnaOcena = data.prosecnaOcena;
        this.ukupnoECTS = data.ukupnoECTS;
      },
      error: (err) => {
        console.error('Greška pri učitavanju istorije studiranja', err);
      }
    });
  }
}