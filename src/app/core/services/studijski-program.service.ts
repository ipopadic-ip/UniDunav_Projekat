import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StudijskiProgram } from '../../core/model/studijski-program.model';

@Injectable({
  providedIn: 'root'
})
export class StudijskiProgramService {
  private apiUrl = 'http://localhost:3000/studijskiProgrami';

  constructor(private http: HttpClient) {}

  getAll(): Observable<StudijskiProgram[]> {
    return this.http.get<StudijskiProgram[]>(this.apiUrl);
  }

  getById(id: number): Observable<StudijskiProgram> {
    return this.http.get<StudijskiProgram>(`${this.apiUrl}/${id}`);
  }

  getByFakultet(fakultetId: number): Observable<StudijskiProgram[]> {
    return this.http.get<StudijskiProgram[]>(`${this.apiUrl}?fakultetId=${fakultetId}`);
  }
}
