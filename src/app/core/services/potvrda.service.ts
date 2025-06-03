import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class PotvrdaService {
  private url = '/api/potvrde';

  constructor(private http: HttpClient) {}

  izdajPotvrdu(data: any): Observable<any> {
    return this.http.post(this.url, data);
  }

  getAll(): Observable<any[]> {
    return this.http.get<any[]>(this.url);
  }
}
