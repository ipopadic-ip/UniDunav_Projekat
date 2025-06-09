import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Fakultet } from '../model/fakultet.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FakultetService {
  private apiUrl = 'http://localhost:8080/api/fakulteti';

  constructor(private http: HttpClient) {}

  getFakulteti(): Observable<Fakultet[]> {
    return this.http.get<Fakultet[]>(this.apiUrl);
  }

  getFakultetById(id: number): Observable<Fakultet> {
    return this.http.get<Fakultet>(`${this.apiUrl}/${id}`);
  }

  createFakultet(fakultet: any): Observable<any> {
    return this.http.post('http://localhost:8080/api/fakulteti', fakultet);
  }

  updateFakultet(id: number, fakultet: any): Observable<any> {
    return this.http.put(`http://localhost:8080/api/fakulteti/${id}`, fakultet);
  }

  activateFakultet(id: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${id}/activate`, {});
  }

  deactivateFakultet(id: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${id}/deactivate`, {});
  }

  getFakultetiAdmin(): Observable<Fakultet[]> {
    return this.http.get<Fakultet[]>(`${this.apiUrl}/admin`);
  }


}
