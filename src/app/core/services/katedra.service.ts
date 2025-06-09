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

  getAllKatedre(): Observable<Katedra[]> {
    return this.http.get<Katedra[]>(`${this.baseUrl}`);
  }

  getAllKatedreAdmin(): Observable<Katedra[]> {
    return this.http.get<Katedra[]>(`${this.baseUrl}/admin`);
  }

  deaktivirajKatedru(id: number): Observable<void> {
    return this.http.patch<void>(`${this.baseUrl}/${id}/deaktiviraj`, {});
  }

  aktivirajKatedru(id: number): Observable<void> {
    return this.http.patch<void>(`${this.baseUrl}/${id}/aktiviraj`, {});
  }

   createKatedra(dto: any): Observable<Katedra> {
    return this.http.post<Katedra>(this.baseUrl, dto);
  }

  updateKatedra(id: number, dto: any): Observable<Katedra> {
    return this.http.put<Katedra>(`${this.baseUrl}/${id}`, dto);
  }


}
