import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Departman } from '../model/departman.model';

@Injectable({
  providedIn: 'root'
})
export class DepartmanService {
  private apiUrl = 'http://localhost:8080/api/departmani';

  constructor(private http: HttpClient) {}

  // Dohvati sve departmane
  getAllDepartmani(): Observable<Departman[]> {
    return this.http.get<Departman[]>(this.apiUrl);
  }

  // Dohvati departmane za određeni fakultet
  getDepartmaniByFakultetId(fakultetId: number): Observable<Departman[]> {
    return this.http.get<Departman[]>(`${this.apiUrl}/fakultet/${fakultetId}`);
  }

  // Dohvati departman po ID-ju
  getDepartmanById(id: number): Observable<Departman> {
    return this.http.get<Departman>(`${this.apiUrl}/${id}`);
  }
}
