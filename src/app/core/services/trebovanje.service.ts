import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class TrebovanjeService {
  private baseUrl = '/api/trebovanja';

  constructor(private http: HttpClient) {}

  getAll(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }

  create(data: any): Observable<any> {
    return this.http.post(this.baseUrl, data);
  }

  potvrdiTrebovanje(id: number): Observable<void> {
  return this.http.put<void>(`${this.baseUrl}/${id}/potvrdi`, {});
}
}