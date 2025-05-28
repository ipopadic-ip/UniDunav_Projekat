import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; // ⬅ dodaj ovo
import { ActivatedRoute } from '@angular/router';
import { Predmet } from '../../../core/model/predmet.model';
import { PredmetService } from '../../../core/services/predmet.service';
import { jsPDF } from 'jspdf';
import { Silabus } from '../../../core/model/silabus.model';
import { SilabusService } from '../../../core/services/silabus.service';

import { NastavniMaterijal } from '../../../core/model/nastavni-materijal.model';
import { NastavniMaterijalService } from '../../../core/services/nastavni-materijal.service';



@Component({
  selector: 'app-predmet-detalji',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './predmet-detalji.component.html',
  styleUrls: ['./predmet-detalji.component.css']
})
export class PredmetDetaljiComponent implements OnInit {
  predmetId!: number;
  predmet?: Predmet;
  // silabus?: { id: number, sadrzaj: string, predmetId: number };
  silabus?: Silabus;
  nastavniMaterijali: NastavniMaterijal[] = [];


  constructor(
    private route: ActivatedRoute,
    private predmetService: PredmetService,
    private silabusService: SilabusService,
    private nastavniMaterijalService: NastavniMaterijalService
  ) {}

  ngOnInit(): void {
    this.predmetId = Number(this.route.snapshot.paramMap.get('id'));
    this.predmetService.getPredmetById(this.predmetId).subscribe(data => {
      this.predmet = data;

      this.silabusService.getSilabusByPredmetId(this.predmetId).subscribe(s => {
        this.silabus = s;
      });

      this.nastavniMaterijalService.getByPredmetId(this.predmetId).subscribe(materials => {
        this.nastavniMaterijali = materials;
      });
    });
    // this.predmetService.getPredmetById(this.predmetId).subscribe(data => {
    //   this.predmet = data;
    // });
    // this.predmetService.getSilabusByPredmetId(this.predmetId).subscribe(silabus => {
    //   this.silabus = silabus;
    // });

  }

  downloadMaterijal(id: number, naziv: string) {
  this.nastavniMaterijalService.download(id).subscribe(blob => {
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = naziv;
    a.click();
    window.URL.revokeObjectURL(url);
  });
}


  downloadSilabus() {
  if (!this.silabus) return;

  const doc = new jsPDF({
    orientation: 'portrait',
    unit: 'mm',
    format: 'a4'
  });

  const marginLeft = 20;
  const marginTop = 20;
  const maxLineWidth = 170; // A4 širina 210mm - 2*20mm margine

  const textLines = doc.splitTextToSize(this.silabus.sadrzaj, maxLineWidth);

  doc.text(textLines, marginLeft, marginTop);
  doc.save(`Silabus_Predmet_${this.predmet?.naziv || 'nepoznat'}.pdf`);
}


  // downloadSilabus() {
  //   if (!this.silabus) return;

  //   const doc = new jsPDF();
  //   doc.text(this.silabus.sadrzaj, 10, 10);
  //   doc.save(`Silabus_Predmet_${this.predmet?.naziv}.pdf`);
  // }


}
