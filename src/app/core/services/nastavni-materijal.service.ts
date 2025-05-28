import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NastavniMaterijal } from '../model/nastavni-materijal.model';

@Injectable({
  providedIn: 'root'
})
export class NastavniMaterijalService {
  private baseUrl = 'http://localhost:8080/api/nastavni-materijali';

  constructor(private http: HttpClient) {}

  getByPredmetId(predmetId: number): Observable<NastavniMaterijal[]> {
    return this.http.get<NastavniMaterijal[]>(`${this.baseUrl}/predmet/${predmetId}`);
  }

  download(id: number): Observable<Blob> {
    return this.http.get(`${this.baseUrl}/preuzmi/${id}`, { responseType: 'blob' });
  }
}
