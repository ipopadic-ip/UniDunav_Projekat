import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BibliotekaService } from '../../../core/services/biblioteka.service';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dodavanje-knjiga',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './dodavanje-knjiga.component.html',
  styleUrls: ['./dodavanje-knjiga.component.css']
})
export class DodavanjeKnjigaComponent implements OnInit {
  aktivnaKartica: 'knjiga' | 'primerak' | 'brisanje' = 'knjiga';

  knjigaForm!: FormGroup;
  primerakForm!: FormGroup;
  brisanjeForm!: FormGroup;

  sveKnjige: any[] = [];
  successMessage = '';
  errorMessage = '';
  pretragaNaziv: string = '';
  filtriraneKnjige: any[] = [];

  constructor(private fb: FormBuilder, private bibliotekaService: BibliotekaService) {}

  ngOnInit(): void {
    this.knjigaForm = this.fb.group({
      naziv: ['', Validators.required],
      autor: ['', Validators.required],
      zanr: ['', Validators.required],
      godinaIzdavanja: ['', [Validators.required, Validators.min(0)]],
      izdavac: ['', Validators.required]
    });

    this.primerakForm = this.fb.group({
      knjigaId: ['', Validators.required],
      isbn: ['', Validators.required],
      dostupan: [true]
    });

    this.brisanjeForm = this.fb.group({
      isbn: ['', Validators.required]
    });

    this.ucitajKnjige();
  }

  prikaziKnjigu() {
    this.aktivnaKartica = 'knjiga';
    this.successMessage = '';
    this.errorMessage = '';
  }

  prikaziPrimerak() {
    this.aktivnaKartica = 'primerak';
    this.successMessage = '';
    this.errorMessage = '';
  }

  prikaziBrisanje() {
    this.aktivnaKartica = 'brisanje';
    this.successMessage = '';
    this.errorMessage = '';
  }

  ucitajKnjige(): void {
    this.bibliotekaService.getSveKnjige().subscribe({
      next: (data) => this.sveKnjige = data,
      error: () => this.errorMessage = 'Greška prilikom učitavanja knjiga.'
    });
  }

  onSubmitKnjiga(): void {
    if (this.knjigaForm.invalid) return;

    const knjiga = { ...this.knjigaForm.value, brojPrimeraka: 0 };

    this.bibliotekaService.dodajKnjigu(knjiga).subscribe({
      next: () => {
        this.successMessage = 'Knjiga uspešno dodata.';
        this.errorMessage = '';
        this.knjigaForm.reset();
        this.ucitajKnjige();
      },
      error: () => {
        this.successMessage = '';
        this.errorMessage = 'Greška pri dodavanju knjige.';
      }
    });
  }

  filtrirajKnjige() {
    const naziv = this.pretragaNaziv.toLowerCase();
    this.filtriraneKnjige = this.sveKnjige.filter(knjiga =>
      knjiga.naziv.toLowerCase().includes(naziv)
    );
  }

  izaberiKnjigu(knjiga: any) {
    this.primerakForm.patchValue({ knjigaId: knjiga.id });
    this.pretragaNaziv = `${knjiga.naziv} - ${knjiga.autor}`;
    this.filtriraneKnjige = [];
  }

  onSubmitPrimerak(): void {
    if (this.primerakForm.invalid) return;

    this.bibliotekaService.dodajPrimerak(this.primerakForm.value).subscribe({
      next: () => {
        this.successMessage = 'Primerak uspešno dodat.';
        this.errorMessage = '';
        this.primerakForm.reset({ knjigaId: '', isbn: '', dostupan: true });
        this.ucitajKnjige();
      },
      error: () => {
        this.successMessage = '';
        this.errorMessage = 'Greška pri dodavanju primerka.';
      }
    });
  }

  onObrisiPrimerak(): void {
  if (this.brisanjeForm.invalid) return;

  const isbn = this.brisanjeForm.value.isbn;

  this.bibliotekaService.obrisiPrimerak(isbn).subscribe({
    next: () => {
      this.successMessage = 'Primerak uspešno obrisan.';
      this.errorMessage = '';
      this.brisanjeForm.reset();
    },
    error: (err) => {
      if (err.status === 404) {
        this.errorMessage = 'Uneli ste nepostojeći ISBN.';
      } else {
        this.errorMessage = 'Greška pri brisanju primerka.';
      }
      this.successMessage = '';
    }
  });
  }
}