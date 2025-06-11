import { Component, inject } from '@angular/core';
import { AdminService } from '../../../core/services/admin.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-eksport',
  imports: [],
  templateUrl: './eksport.component.html',
  styleUrl: './eksport.component.css'
})
export class EksportComponent {
  private router = inject(Router);
   constructor(private adminService: AdminService) {}

  download(type: 'pdf' | 'xml') {
    const downloadFn = type === 'pdf' ? this.adminService.downloadPdf() : this.adminService.downloadXml();
    downloadFn.subscribe(blob => {
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = type === 'pdf' ? 'studenti.pdf' : 'studenti.xml';
      a.click();
      URL.revokeObjectURL(url);
    });
  }

  downloadProfesor(type: 'pdf' | 'xml') {
    const downloadFn = type === 'pdf' ? this.adminService.downloadPdfProfesor() : this.adminService.downloadXmlProfesor();
    downloadFn.subscribe(blob => {
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement('a');
      a.href = url;
      a.download = type === 'pdf' ? 'profesori.pdf' : 'profesori.xml';
      a.click();
      URL.revokeObjectURL(url);
    });
  }

  goBack() {
    this.router.navigate(['/admin/get-user']);
  }

}
