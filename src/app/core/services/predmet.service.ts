import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Predmet } from '../model/predmet.model';



@Injectable({
  providedIn: 'root'
})
export class PredmetService {
  private baseUrl = 'http://localhost:8080/api/student';

  private apiUrl = 'http://localhost:8080/api/predmeti';

  constructor(private http: HttpClient) {}

  getPredmetiZaStudenta(studentId: number): Observable<Predmet[]> {
    return this.http.get<Predmet[]>(`${this.baseUrl}/${studentId}/predmeti`);

    
  }

  getPredmetById(id: number): Observable<Predmet> {
  return this.http.get<Predmet>(`${this.apiUrl}/${id}`);
``}

getSviPredmeti(): Observable<Predmet[]> {
  return this.http.get<Predmet[]>('/api/predmeti');
}

getAll(): Observable<Predmet[]> {
  return this.http.get<Predmet[]>(`${this.apiUrl}`);
}

getAllPredmetiAdmin(): Observable<Predmet[]> {
  return this.http.get<Predmet[]>(`${this.apiUrl}/admin`);
}

aktivirajPredmet(id: number): Observable<Predmet> {
  return this.http.put<Predmet>(`${this.apiUrl}/${id}/aktiviraj`, {});
}

deaktivirajPredmet(id: number): Observable<Predmet> {
  return this.http.put<Predmet>(`${this.apiUrl}/${id}/deaktiviraj`, {});
}

createPredmet(predmet: Predmet): Observable<Predmet> {
  return this.http.post<Predmet>(`${this.apiUrl}`, predmet);
}

updatePredmet(id: number, predmet: Predmet): Observable<Predmet> {
  return this.http.put<Predmet>(`${this.apiUrl}/${id}`, predmet);
}


}