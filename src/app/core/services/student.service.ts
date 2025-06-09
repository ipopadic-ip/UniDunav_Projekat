import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  private apiUrl = '/api/student'; 

  constructor(private http: HttpClient) {}

  getIstorijaStudiranja(studentId: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${studentId}/istorija`);
  }

  pretraziPoBrojuIndeksa(indeks: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/pretraga?indeks=${indeks}`);
  }


  // Možeš ovde dodavati i druge metode za studenta kasnije
}