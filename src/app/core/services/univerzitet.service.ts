import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Univerzitet } from '../model/univerzitet.model';

@Injectable({
  providedIn: 'root',
})
export class UniverzitetService {
  private apiUrl = 'http://localhost:3000/univerzitet';

  constructor(private http: HttpClient) {}

  getUniverzitet(): Observable<Univerzitet> {
    return this.http.get<Univerzitet>(this.apiUrl);
  }
  
}
