import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EvaluacijaService {
  private apiUrl = '/api/evaluacija-znanja';

  constructor(private http: HttpClient) {}

  kreirajEvaluaciju(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/kreiraj`, data);
  }
}