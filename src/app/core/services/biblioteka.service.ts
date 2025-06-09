import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BibliotekaService {
  private apiUrl = '/api/biblioteka';

  constructor(private http: HttpClient) {}

  // Pretraga dostupnih knjiga
  getDostupneKnjige(naziv: string = ''): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/knjige?naziv=${naziv}`);
  }

  // Pretraga iznajmljivanja po indeksu
  getIznajmljivanjaStudenta(indeks: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/student/${indeks}/iznajmljivanja`);
  }

  // Izdavanje knjige
  izdajKnjigu(primerakId: number, brojIndeksa: string): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/izdaj`, {
      primerakId,
      brojIndeksa
    });
  }

  // Vraćanje knjige
  vratiKnjigu(iznajmljivanjeId: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/vrati`, {
      iznajmljivanjeId
    });
  }
 getDostupneKnjigeZaStudenta(indeks: string): Observable<any[]> {
  return this.http.get<any[]>(`${this.apiUrl}/student/${indeks}/knjige`);
}
 
  dodajKnjigu(knjiga: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/knjige`, knjiga);
  }

  
  dodajPrimerak(primerak: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/primerci`, primerak);
  }

  
  getSveKnjige(): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/knjige`);
  }
  obrisiPrimerak(isbn: string): Observable<any> {
  return this.http.delete<any>(`${this.apiUrl}/primerak/${isbn}`);
}
}