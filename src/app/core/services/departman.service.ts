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

  // Dohvati sve departmane za admina (aktivni + neaktivni, sortirani)
  getAllDepartmaniAdmin(): Observable<Departman[]> {
    return this.http.get<Departman[]>(`${this.apiUrl}/admin`);
  }

  createDepartman(dto: any): Observable<Departman> {
    return this.http.post<Departman>(this.apiUrl, dto);
  }

  updateDepartman(id: number, dto: any): Observable<Departman> {
    return this.http.put<Departman>(`${this.apiUrl}/${id}`, dto);
  }

  // Deaktiviraj departman
  deaktivirajDepartman(id: number): Observable<void> {
    return this.http.patch<void>(`${this.apiUrl}/${id}/deaktiviraj`, {});
  }

  // Aktiviraj departman
  aktivirajDepartman(id: number): Observable<void> {
    return this.http.patch<void>(`${this.apiUrl}/${id}/aktiviraj`, {});
  }

}
