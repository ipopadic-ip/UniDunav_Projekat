import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { VerzijaDokumenta } from '../model/verzija-dokumenta.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class VerzijaDokumentaService {
  private apiUrl = '/api/verzije';

  constructor(private http: HttpClient) {}

  getVerzijeAdmin(dokumentId: number): Observable<VerzijaDokumenta[]> {
    return this.http.get<VerzijaDokumenta[]>(`${this.apiUrl}/admin/${dokumentId}`);
  }

  getSveVerzijeAdminSve(): Observable<VerzijaDokumenta[]> {
  return this.http.get<VerzijaDokumenta[]>(`${this.apiUrl}/admin`);
}

  uploadVerzija(dokumentId: number, file: File, email: string): Observable<any> {
    const formData = new FormData();
    formData.append('file', file);
    formData.append('email', email);
    return this.http.post(`${this.apiUrl}/${dokumentId}/upload`, formData);
  }

  dodajTekstualnuVerziju(dokumentId: number, verzija: { autor: string; sadrzaj: string }): Observable<VerzijaDokumenta> {
    return this.http.post<VerzijaDokumenta>(`${this.apiUrl}/${dokumentId}/tekst-verzija`, verzija);
  }

  izmeniVerziju(id: number, verzija: Partial<VerzijaDokumenta>): Observable<VerzijaDokumenta> {
    return this.http.put<VerzijaDokumenta>(`${this.apiUrl}/verzija/${id}`, verzija);
  }

  deaktiviraj(id: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${id}/deaktiviraj`, {});
  }

  reaktiviraj(id: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${id}/reaktiviraj`, {});
  }
  getPoslednjaVerzija(dokumentId: number): Observable<VerzijaDokumenta> {
    return this.http.get<VerzijaDokumenta>(`/api/verzije/poslednja/${dokumentId}`);
  }

  getVerzije(dokumentId: number): Observable<any[]> {
    return this.http.get<any[]>(`/api/verzije/dokument/${dokumentId}`);
  }
  getPoslednjeVerzije(): Observable<VerzijaDokumenta[]> {
    return this.http.get<VerzijaDokumenta[]>(`${this.apiUrl}`);
  }
}
