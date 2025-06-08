import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TipEvaluacijeService {
  private readonly apiUrl = '/api/tip-evaluacije';

  constructor(private http: HttpClient) {}

  // Dobavlja sve tipove evaluacije (kolokvijum, ispit, itd.)
  getAll(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}