import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Student } from '../model/student.model';


@Injectable({
  providedIn: 'root'
})
export class StudentService {
  private apiUrl = '/api/student'; 

  constructor(private http: HttpClient) {}

  getIstorijaStudiranja(studentId: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${studentId}/istorija`);
  }

  getStudentiZaNastavnika(): Observable<Student[]> {
    return this.http.get<Student[]>(`${this.apiUrl}/nastavnik/studenti`);
  }

}