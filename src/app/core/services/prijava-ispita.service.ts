import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PrijavaIspitaDTO } from '../model/prijava-ispita.model';

@Injectable({ providedIn: 'root' })
export class PrijavaIspitaService {
  private readonly API_URL = '/api/prijave-ispita';

  constructor(private http: HttpClient) {}

  getDostupnePrijave(studentId: number): Observable<PrijavaIspitaDTO[]> {
    return this.http.get<PrijavaIspitaDTO[]>(`${this.API_URL}/dostupne/${studentId}`);
  }

  prijavi(prijavaId: number): Observable<any> {
    return this.http.post(`${this.API_URL}/prijavi/${prijavaId}`, {});
  }
}