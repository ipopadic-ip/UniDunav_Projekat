import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; // ← ovo je KLJUČNO
import { StudentService } from '../../../core/services/student.service';
import { Student } from '../../../core/model/student.model';

@Component({
  selector: 'app-studenti',
  standalone: true, // ← moraš dodati ovo
  imports: [CommonModule, FormsModule], // ← i ovo da bi ngModel radio
  templateUrl: './studenti.component.html',
  styleUrls: ['./studenti.component.css']
})
export class StudentiComponent implements OnInit {
  studenti: Student[] = [];
  searchTerm: string = '';

  constructor(private studentService: StudentService) {}

  ngOnInit(): void {
    this.studentService.getStudentiZaNastavnika().subscribe(data => {
      this.studenti = data;
    });
  }

  get filtriraniStudenti(): Student[] {
    return this.studenti.filter(s =>
      (`${s.user.ime} ${s.user.prezime} ${s.brojIndeksa}`.toLowerCase())
        .includes(this.searchTerm.toLowerCase())
    );
  }

  otvoriDetalje(id: number): void {
    console.log("Detalji za studenta:", id);
  }
}
