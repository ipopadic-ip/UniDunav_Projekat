import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PrijavaIspitaDTO } from '../model/prijava-ispita.model';
import { Predmet } from '../model/predmet.model';

@Injectable({ providedIn: 'root' })
export class PrijavaIspitaService {
  private readonly API_URL = '/api/prijave-ispita';

  constructor(private http: HttpClient) {}

  getDostupnePrijave(studentId: number): Observable<PrijavaIspitaDTO[]> {
  console.log('🛰 Pozivam GET /dostupne/' + studentId);
  return this.http.get<PrijavaIspitaDTO[]>(`${this.API_URL}/dostupne/${studentId}`);
}

  prijavi(prijavaId: number): Observable<any> {
    return this.http.post(`${this.API_URL}/prijavi/${prijavaId}`, {});
  }

  kreirajPrijave(dto: { predmetId: number, datumIspita: string }): Observable<string> {
  return this.http.post(`${this.API_URL}/kreiraj`, dto, { responseType: 'text' });
}
getSviPredmeti(): Observable<Predmet[]> {
  return this.http.get<Predmet[]>('/api/predmeti');
}
}