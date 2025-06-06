import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Predmet } from '../model/predmet.model';



@Injectable({
  providedIn: 'root'
})
export class PredmetService {
  private baseUrl = 'http://localhost:8080/api/student';

  private baseUrl1 = 'http://localhost:8080/api/profesor';

  private apiUrl = 'http://localhost:8080/api/predmeti';

  constructor(private http: HttpClient) {}

  getPredmetiZaStudenta(studentId: number): Observable<Predmet[]> {
    return this.http.get<Predmet[]>(`${this.baseUrl}/${studentId}/predmeti`);
  }

  getPredmetiZaProfesora(profesorId: number): Observable<any[]> {
    return this.http.get<Predmet[]>(`${this.baseUrl1}/${profesorId}/predmeti`);
  }

  getPredmetById(id: number): Observable<Predmet> {
  return this.http.get<Predmet>(`${this.apiUrl}/${id}`);
``}
}