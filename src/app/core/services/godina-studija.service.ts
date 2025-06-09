import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GodinaStudija } from '../model/godina-studija.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GodinaStudijaService {
  private apiUrl = 'http://localhost:8080/api/godinestudija';

  constructor(private http: HttpClient) {}

  getAllZaAdmin(): Observable<{ [program: string]: GodinaStudija[] }> {
    return this.http.get<{ [program: string]: GodinaStudija[] }>(`${this.apiUrl}/admin`);
  }

   getAll(): Observable<GodinaStudija[]> {
    return this.http.get<GodinaStudija[]>(this.apiUrl);
  }

//   getAll(): Observable<{ [program: string]: GodinaStudija[] }> {
//     return this.http.get<{ [program: string]: GodinaStudija[] }>(`${this.apiUrl}`);
//   }

  deaktivirajGodinu(id: number): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}/deaktiviraj`, {});
  }

  aktivirajGodinu(id: number): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}/aktiviraj`, null);
  }

   getById(id: number): Observable<GodinaStudija> {
    return this.http.get<GodinaStudija>(`${this.apiUrl}/${id}`);
  }

  create(dto: { godina: number; studijskiProgramId: number }): Observable<GodinaStudija> {
    return this.http.post<GodinaStudija>(this.apiUrl, dto);
  }

  update(id: number, dto: { godina: number; studijskiProgramId: number }): Observable<GodinaStudija> {
    return this.http.put<GodinaStudija>(`${this.apiUrl}/${id}`, dto);
  }
}
