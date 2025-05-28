import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Predmet } from '../model/predmet.model';

@Injectable({
  providedIn: 'root'
})
export class PredmetService {
  private apiUrl = 'http://localhost:8080/api/predmeti';

  constructor(private http: HttpClient) {}

  getPredmetById(id: number): Observable<Predmet> {
    return this.http.get<Predmet>(`${this.apiUrl}/${id}`);
  }

//   getSilabusByPredmetId(predmetId: number) {
//   return this.http.get<{ id: number, sadrzaj: string, predmetId: number }>(`${this.apiUrl}/api/silabus/predmet/${predmetId}`);
// }

}
