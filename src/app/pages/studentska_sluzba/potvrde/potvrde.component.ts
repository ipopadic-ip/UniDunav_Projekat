import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { StudentService } from '../../../core/services/student.service';
import { PotvrdaService } from '../../../core/services/potvrda.service';

@Component({
  selector: 'app-potvrde',
  standalone: true,
  templateUrl: './potvrde.component.html',
  styleUrls: ['./potvrde.component.css'],
  imports: [CommonModule, ReactiveFormsModule, FormsModule] 
})
export class PotvrdeComponent implements OnInit {
  forma!: FormGroup;
  studenti: any[] = [];
  potvrde: any[] = [];
  pretraga: string = '';

  constructor(
    private fb: FormBuilder,
    private studentService: StudentService,
    private potvrdaService: PotvrdaService
  ) {}

  ngOnInit(): void {
    this.forma = this.fb.group({
      studentId: [null, Validators.required],
      tip: ['', Validators.required],
      tekst: ['', [Validators.required, Validators.maxLength(1000)]]
    });

    this.ucitajPotvrde();
  }

  pretraziStudente(): void {
    if (this.pretraga.length < 2) return;
    this.studentService.pretraziPoBrojuIndeksa(this.pretraga).subscribe(res => {
      this.studenti = res;
    });
  }

  izdajPotvrdu(): void {
    if (this.forma.invalid) return;

    this.potvrdaService.izdajPotvrdu(this.forma.value).subscribe(() => {
      this.forma.reset();
      this.studenti = [];
      this.ucitajPotvrde();
    });
  }

  private ucitajPotvrde(): void {
    this.potvrdaService.getAll().subscribe(res => {
      this.potvrde = res;
    });
  }
}