import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TipStudijaService } from '../../../../core/services/tip-studija.service';
import { Router } from '@angular/router';
// import { TipStudija } from '../../../../core/model/tip-studija.model';
import { TipStudijaRDF } from '../../../../core/model/TipStudijaRDF.model';
import { FormsModule } from '@angular/forms';
import { FormControl } from '@angular/forms';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
import { ReactiveFormsModule } from '@angular/forms';


@Component({
  selector: 'app-tip-studija-list',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './tip-studija-list.component.html',
  styleUrls: ['./tip-studija-list.component.css'],
})
export class TipStudijaListComponent implements OnInit {
  private tipStudijaService = inject(TipStudijaService);
  private router = inject(Router);
  searchControl = new FormControl('');

  pretragaDeleted: string = 'all';

  // tipoviStudija: string[] = [];
  tipoviStudija: TipStudijaRDF[] = [];
  pretraga: string = '';


  ngOnInit(): void {
    this.ucitajTipoveStudija();

     this.searchControl.valueChanges
      .pipe(debounceTime(300), distinctUntilChanged())
      .subscribe(value => {
        console.log('Debounced pretraga:', value);
        if (!value?.trim()) {
          this.ucitajTipoveStudija();
        } else {
          this.tipStudijaService.pretragaSparql(value).subscribe({
            next: data => (this.tipoviStudija = data),
            error: err => console.error('Greška pri SPARQL pretrazi:', err),
          });
        }
      });
  }

  // pretraziSPARQLPoDeleted() {
  //   if (!this.pretragaDeleted.trim()) {
  //     this.ucitajTipoveStudija();
  //     return;
  //   }

  //   this.tipStudijaService.pretragaPoDeleted(this.pretragaDeleted).subscribe({
  //     next: (data) => (this.tipoviStudija = data),
  //     error: (err) =>
  //       console.error('Greška pri SPARQL pretrazi deleted vrednosti:', err),
  //   });
  // }

  pretraziSPARQLPoDeleted() {
  if (this.pretragaDeleted === 'all') {
    this.ucitajTipoveStudija();
    return;
  }

  this.tipStudijaService.pretragaPoDeleted(this.pretragaDeleted).subscribe({
    next: (data) => (this.tipoviStudija = data),
    error: (err) =>
      console.error('Greška pri SPARQL pretrazi deleted vrednosti:', err),
  });
}

  ucitajTipoveStudija() {
    this.tipStudijaService.getAllAdmin().subscribe({
      next: (data) => (this.tipoviStudija = data),
      error: (err) =>
        console.error('Greška pri učitavanju tipova studija:', err),
    });
  }

   get filtriraniTipoviStudija(): TipStudijaRDF[] {
    if (!this.pretraga.trim()) return this.tipoviStudija;
    const q = this.pretraga.toLowerCase();
    return this.tipoviStudija.filter(t =>
      t.tip.toLowerCase().includes(q)
    );
  }

  deaktiviraj(tip: string) {
  this.tipStudijaService.deaktiviraj(tip).subscribe({
    next: () => this.ucitajTipoveStudija(),
    error: (err) => console.error('Greška pri deaktivaciji:', err),
  });
}

aktiviraj(tip: string) {
  this.tipStudijaService.aktiviraj(tip).subscribe({
    next: () => this.ucitajTipoveStudija(),
    error: (err) => console.error('Greška pri aktivaciji:', err),
  });
}


  dodajTip() {
    this.router.navigate(['/admin/tip-studija/add']);
  }

  izmeni(tipStudija: TipStudijaRDF) {
  this.router.navigate(['/admin/tip-studija/edit', encodeURIComponent(tipStudija.uri)]);
}

pretraziSPARQL() {
  console.log('Pozivam SPARQL sa:', this.pretraga);
  if (!this.pretraga.trim()) {
    this.ucitajTipoveStudija(); // fallback ako je prazan unos
    return;
  }

  this.tipStudijaService.pretragaSparql(this.pretraga)
  .pipe(debounceTime(300), distinctUntilChanged())
  .subscribe({
    next: (data) => (this.tipoviStudija = data),
    error: (err) =>
      console.error('Greška pri SPARQL pretrazi tipova studija:', err),
  });
}



//   izmeni(uri: string) {
//   this.router.navigate(['/admin/tip-studija/edit', encodeURIComponent(uri)]);
// }


//   izmeni(tip: string) {
//   this.router.navigate(['/admin/tip-studija/edit', encodeURIComponent(tip)]);
// }



  goBack() {
    this.router.navigate(['/admin']);
  }
}


// import { Component, OnInit, inject } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { TipStudijaService } from '../../../../core/services/tip-studija.service';
// import { TipStudija } from '../../../../core/model/tip-studija.model';
// import { Router } from '@angular/router';

// @Component({
//   selector: 'app-tip-studija-list',
//   standalone: true,
//   imports: [CommonModule],
//   templateUrl: './tip-studija-list.component.html',
//   styleUrls: ['./tip-studija-list.component.css']
// })
// export class TipStudijaListComponent implements OnInit {
//   private tipStudijaService = inject(TipStudijaService);
//   private router = inject(Router);

//   tipoviStudija: TipStudija[] = [];

//   ngOnInit(): void {
//     this.ucitajTipoveStudija();
//   }

//   ucitajTipoveStudija() {
//     this.tipStudijaService.getAllAdmin().subscribe({
//       next: (data) => this.tipoviStudija = data,
//       error: (err) => console.error('Greška pri učitavanju tipova studija:', err)
//     });
//   }

//   deaktiviraj(id: number) {
//     this.tipStudijaService.deaktiviraj(id).subscribe({
//       next: () => this.ucitajTipoveStudija(),
//       error: (err) => console.error('Greška pri deaktivaciji:', err)
//     });
//   }

//   aktiviraj(id: number) {
//     this.tipStudijaService.aktiviraj(id).subscribe({
//       next: () => this.ucitajTipoveStudija(),
//       error: (err) => console.error('Greška pri aktivaciji:', err)
//     });
//   }

//   dodajTip() {
//     this.router.navigate(['/admin/tip-studija/add']);
//   }

//   izmeni(id: number) {
//     this.router.navigate(['/admin/tip-studija/edit', id]);
//   }

//   goBack() {
//     this.router.navigate(['/admin']);
//   }
// }
