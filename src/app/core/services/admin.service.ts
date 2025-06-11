import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private apiUrl = 'http://localhost:8080/api/admin';

  constructor(private http: HttpClient) {}

  downloadPdf(): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/export/students/pdf`, { responseType: 'blob' });
  }

  downloadXml(): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/export/students/xml`, { responseType: 'blob' });
  }

  downloadPdfProfesor(): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/export/profesors/pdf`, { responseType: 'blob' });
  }

  downloadXmlProfesor(): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/export/profesors/xml`, { responseType: 'blob' });
  }

}
