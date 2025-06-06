import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfesorService {

  private apiUrl = '/api/profesor';

  constructor(private http: HttpClient) {}

  getPredmetiZaProfesora(profesorId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/${profesorId}/predmeti`);
  }
}
