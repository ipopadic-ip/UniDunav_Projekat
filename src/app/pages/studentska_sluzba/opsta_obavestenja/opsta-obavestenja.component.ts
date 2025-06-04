import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { OpstaObavestenjaService } from '../../../core/services/opsta-obavestenja.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-opsta-obavestenja',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, HttpClientModule],
  templateUrl: './opsta-obavestenja.component.html',
  styleUrls: ['./opsta-obavestenja.component.css']
})
export class OpstaObavestenjaComponent implements OnInit {
  forma!: FormGroup;
  obavestenja: any[] = [];

  constructor(private fb: FormBuilder, private service: OpstaObavestenjaService) {}

  ngOnInit(): void {
    this.forma = this.fb.group({
      naslov: ['', Validators.required],
      tekst: ['', Validators.required]
    });

    this.ucitajObavestenja();
  }

  ucitajObavestenja(): void {
    this.service.getAll().subscribe(data => this.obavestenja = data);
  }

  kreiraj(): void {
    if (this.forma.invalid) return;

    this.service.create(this.forma.value).subscribe(() => {
      this.forma.reset();
      this.ucitajObavestenja();
    });
  }

  obrisi(id: number): void {
    this.service.delete(id).subscribe(() => this.ucitajObavestenja());
  }
}