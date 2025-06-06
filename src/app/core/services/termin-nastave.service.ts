import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface TerminNastaveDTO {
  id?: number;
  profesorPredmetId: number;
  ishod: string;
  terminPocetka: string;
  terminZavrsetka: string;
}

export interface Profesor {
  id: number;
  ime: string;
  prezime: string;
}

export interface ProfesorPredmet {
  id: number;
  predmetNaziv: string;
}

export interface Ishod {
  id: number;
  opis: string;
}

@Injectable({
  providedIn: 'root'
})
export class TerminNastaveService {
  private baseUrl = '/api/termin-nastave';

  constructor(private http: HttpClient) {}

  kreirajTermin(dto: TerminNastaveDTO): Observable<TerminNastaveDTO> {
    return this.http.post<TerminNastaveDTO>(this.baseUrl, dto);
  }

  izmeniTermin(id: number, dto: TerminNastaveDTO): Observable<TerminNastaveDTO> {
    return this.http.put<TerminNastaveDTO>(`${this.baseUrl}/${id}`, dto);
  }

  obrisiTermin(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }

  getTerminiZaProfesora(profesorId: number): Observable<TerminNastaveDTO[]> {
    return this.http.get<TerminNastaveDTO[]>(`${this.baseUrl}/profesor/${profesorId}`);
  }

  getProfesori(): Observable<Profesor[]> {
  return this.http.get<Profesor[]>('/api/profesor/profesori');
}

  getPredmetiZaProfesora(profesorId: number): Observable<ProfesorPredmet[]> {
  return this.http.get<ProfesorPredmet[]>(`/api/profesor-predmet/profesor/${profesorId}`);
}


  getSviIshodi(): Observable<Ishod[]> {
    return this.http.get<Ishod[]>('/api/ishodi');
  }
}