import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Katedra } from '../model/katedra.model';

@Injectable({
  providedIn: 'root'
})
export class KatedraService {
  private baseUrl = 'http://localhost:8080/api/katedre';

  constructor(private http: HttpClient) {}

  getKatedreByDepartmanId(departmanId: number): Observable<Katedra[]> {
    return this.http.get<Katedra[]>(`${this.baseUrl}/departman/${departmanId}`);
  }

  getKatedraById(id: number): Observable<Katedra> {
  return this.http.get<Katedra>(`${this.baseUrl}/${id}`);
}

}
