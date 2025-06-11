import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Dokument } from '../model/dokument.model';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DokumentService {
  private apiUrl = '/api/dokumenti';

  constructor(private http: HttpClient) {}

  getAllDokumentiAdmin(): Observable<Dokument[]> {
    return this.http.get<Dokument[]>(`${this.apiUrl}/admin`);
  }

  deaktiviraj(id: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${id}/deaktiviraj`, {});
  }

  aktiviraj(id: number): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${id}/aktiviraj`, {});
  }

  createDokument(dokument: Partial<Dokument>) {
  return this.http.post<Dokument>(`${this.apiUrl}`, dokument);
}

updateDokument(id: number, dokument: Partial<Dokument>) {
  return this.http.put<Dokument>(`${this.apiUrl}/${id}`, dokument);
}
getAllAktivni(): Observable<Dokument[]> {
  return this.http.get<Dokument[]>(this.apiUrl); // /api/dokumenti
}
getAktivniDokumentById(id: number): Observable<Dokument | undefined> {
  return this.getAllAktivni().pipe(
    map((dokumenti) => dokumenti.find((d) => d.id === id))
  );
}

getDokumentById(id: number) {
  return this.http.get<Dokument[]>(`${this.apiUrl}/admin`).pipe(
    map((dokumenti) => dokumenti.find((d) => d.id === id)!)
  );
}


}
