// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable } from 'rxjs';
// import { StudijskiProgram } from '../../core/model/studijski-program.model';

// @Injectable({
//   providedIn: 'root'
// })
// export class StudijskiProgramService {
//   private apiUrl = 'http://localhost:3000/studijskiProgrami';

//   constructor(private http: HttpClient) {}

//   getAll(): Observable<StudijskiProgram[]> {
//     return this.http.get<StudijskiProgram[]>(this.apiUrl);
//   }

//   getById(id: number): Observable<StudijskiProgram> {
//     return this.http.get<StudijskiProgram>(`${this.apiUrl}/${id}`);
//   }

//   getByFakultet(fakultetId: number): Observable<StudijskiProgram[]> {
//     return this.http.get<StudijskiProgram[]>(`${this.apiUrl}?fakultetId=${fakultetId}`);
//   }
// }

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { StudijskiProgram } from '../model/studijski-program.model';
import { GrupisaniProgrami } from '../model/GrupisaniProgrami.model';

@Injectable({
  providedIn: 'root'
})
export class StudijskiProgramService {
  private baseUrl = 'http://localhost:8080/api/studijskiprogrami';

  constructor(private http: HttpClient) {}

   getAll(): Observable<StudijskiProgram[]> {
    return this.http.get<StudijskiProgram[]>(`${this.baseUrl}`);
  }

  getAllAdmin(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/admin`);
  }

  deaktiviraj(id: number): Observable<void> {
    return this.http.patch<void>(`${this.baseUrl}/${id}/deaktiviraj`, {});
  }

  aktiviraj(id: number): Observable<void> {
    return this.http.patch<void>(`${this.baseUrl}/${id}/aktiviraj`, {});
  }

  getById(id: number): Observable<StudijskiProgram> {
    return this.http.get<StudijskiProgram>(`${this.baseUrl}/${id}`);
  }

  create(dto: Partial<StudijskiProgram>): Observable<StudijskiProgram> {
    return this.http.post<StudijskiProgram>(`${this.baseUrl}`, dto);
  }

  update(id: number, dto: Partial<StudijskiProgram>): Observable<StudijskiProgram> {
    return this.http.put<StudijskiProgram>(`${this.baseUrl}/${id}`, dto);
  }

  // Ova metoda odgovara tvojoj fancy backend metodi
  // getStudijskiProgramiGroupedByTip(katedraId: number): Observable<Record<string, StudijskiProgram[]>> {
  //   return this.http.get<Record<string, StudijskiProgram[]>>(`${this.baseUrl}/katedra/${katedraId}/grupisani-po-tipu`);
  // }
  // getStudijskiProgramiGroupedByTip(katedraId: number): Observable<Record<string, StudijskiProgram[]>> {
  //   return this.http.get<Record<string, StudijskiProgram[]>>(`${this.baseUrl}/katedra/${katedraId}/grupisani-po-tipu`)
  //     .pipe(tap(data => console.log('Grouped SP data', data)));
  // }

  getStudijskiProgramiGroupedByTip(katedraId: number): Observable<GrupisaniProgrami[]> {
  return this.http.get<GrupisaniProgrami[]>(`${this.baseUrl}/katedra/${katedraId}/grupisani-po-tipu`);
}

  getStudijskiProgramById(id: number): Observable<StudijskiProgram> {
  return this.http.get<StudijskiProgram>(`${this.baseUrl}/${id}`);
}

getPredmetiPoGodinama(studijskiProgramId: number): Observable<Record<number, { id: number, naziv: string, ects: number }[]>> {
  return this.http.get<Record<number, { id: number, naziv: string, ects: number }[]>>(
    `http://localhost:8080/api/studijskiprogrami/${studijskiProgramId}/predmeti-po-godinama`
  );
}


}
