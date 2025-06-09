import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { TipStudijaService } from '../../../../core/services/tip-studija.service';
import { TipStudijaRDF } from '../../../../core/model/TipStudijaRDF.model';

@Component({
  selector: 'app-tip-studija-form',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './tip-studija-form.component.html',
})
export class TipStudijaFormComponent implements OnInit {
  private tipStudijaService = inject(TipStudijaService);
  private route = inject(ActivatedRoute);
  private router = inject(Router);

  tipStudija: Partial<TipStudijaRDF> = {};
  originalTip = '';
  originalUri = '';
  isEdit = false;

  ngOnInit() {
    const rawUri = this.route.snapshot.paramMap.get('uri');
    const decodedUri = rawUri ? decodeURIComponent(rawUri) : null;

    if (decodedUri) {
      this.isEdit = true;
      this.originalUri = decodedUri;
      this.tipStudijaService.getByUri(decodedUri).subscribe({
        next: (tipDto) => {
          this.tipStudija = tipDto;
        },
        error: (err) => {
          console.error('Greška pri učitavanju tipa studija', err);
          alert('Greška pri učitavanju tipa studija.');
          this.router.navigate(['/admin/tip-studija']);
        }
      });
    }
  }

  sacuvaj() {
    const noviTip = this.tipStudija.tip?.trim();

    if (!noviTip) {
      alert('Naziv tipa studija je obavezan.');
      return;
    }

    if (this.isEdit && this.originalUri) {
      this.tipStudijaService.updateByUri(this.originalUri, noviTip).subscribe({
        next: () => {
          alert('Uspešno izmenjen tip studija.');
          this.router.navigate(['/admin/tip-studija']);
        },
        error: () => alert('Greška pri izmeni tipa studija.')
      });
    } else {
      this.tipStudijaService.create(noviTip).subscribe({
        next: () => {
          alert('Uspešno dodat tip studija.');
          this.router.navigate(['/admin/tip-studija']);
        },
        error: () => alert('Greška pri dodavanju tipa studija.')
      });
    }
  }

  // ngOnInit() {
  //   const rawUri = this.route.snapshot.paramMap.get('tip'); // zadrži isti parametar
  //   const uri = rawUri ? decodeURIComponent(rawUri) : null;

  //   if (uri) {
  //     this.isEdit = true;
  //     this.originalTip = uri;
  //     this.tipStudijaService.getByUri(uri).subscribe({
  //       next: (tip) => {
  //         this.tipStudija.tip = tip.tip; // jer sad vraća objekat
  //       },
  //       error: (err) => {
  //         console.error('Greška pri učitavanju tipa studija', err);
  //         alert('Greška pri učitavanju tipa studija.');
  //         this.router.navigate(['/admin/tip-studija']);
  //       },
  //     });
  //   }
  // }

  // sacuvaj() {
  //   if (!this.tipStudija.tip || this.tipStudija.tip.trim() === '') {
  //     alert('Naziv tipa studija je obavezan.');
  //     return;
  //   }

  //   const noviTip = this.tipStudija.tip.trim();

  //   if (this.isEdit) {
  //     this.tipStudijaService.update(this.originalTip, noviTip).subscribe({
  //       next: () => {
  //         alert('Tip studija uspešno izmenjen.');
  //         this.router.navigate(['/admin/tip-studija']);
  //       },
  //       error: () => alert('Greška pri izmeni tipa studija.'),
  //     });
  //   } else {
  //     this.tipStudijaService.create(noviTip).subscribe({
  //       next: () => {
  //         alert('Tip studija uspešno dodat.');
  //         this.router.navigate(['/admin/tip-studija']);
  //       },
  //       error: () => alert('Greška pri dodavanju tipa studija.'),
  //     });
  //   }
  // }

  goBack() {
    this.router.navigate(['/admin/tip-studija']);
  }

  otkazi() {
    this.router.navigate(['/admin/tip-studija']);
  }
}



// import { Component, inject } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { FormsModule } from '@angular/forms';
// import { ActivatedRoute, Router } from '@angular/router';
// import { TipStudijaService } from '../../../../core/services/tip-studija.service';
// import { TipStudija } from '../../../../core/model/tip-studija.model';

// @Component({
//   selector: 'app-tip-studija-form',
//   standalone: true,
//   imports: [CommonModule, FormsModule],
//   templateUrl: './tip-studija-form.component.html',
// })
// export class TipStudijaFormComponent {
//   private tipStudijaService = inject(TipStudijaService);
//   private route = inject(ActivatedRoute);
//   private router = inject(Router);

//   tipStudija: Partial<TipStudija> = {};
//   isEdit = false;

//   ngOnInit() {
//     const id = this.route.snapshot.paramMap.get('id');
//     if (id) {
//       this.isEdit = true;
//       this.tipStudijaService.getById(+id).subscribe({
//         next: (data) => this.tipStudija = data,
//         error: (err) => console.error('Greška pri učitavanju tipa studija', err),
//       });
//     }
//   }

//   sacuvaj() {
//     if (!this.tipStudija.tip) {
//       alert('Naziv tipa studija je obavezan.');
//       return;
//     }

//     if (this.isEdit && this.tipStudija.id) {
//       this.tipStudijaService.update(this.tipStudija.id, this.tipStudija).subscribe({
//         next: () => {
//           alert('Tip studija uspešno izmenjen.');
//           this.router.navigate(['/admin/tip-studija']);
//         },
//         error: () => alert('Greška pri izmeni tipa studija.'),
//       });
//     } else {
//       this.tipStudijaService.create(this.tipStudija).subscribe({
//         next: () => {
//           alert('Tip studija uspešno dodat.');
//           this.router.navigate(['/admin/tip-studija']);
//         },
//         error: () => alert('Greška pri dodavanju tipa studija.'),
//       });
//     }
//   }

//   goBack() {
//     this.router.navigate(['/admin/tip-studija']);
//   }

//   otkazi() {
//     this.router.navigate(['/admin/tip-studija']);
//   }
// }
