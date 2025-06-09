import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
// import { TipStudija } from '../model/tip-studija.model';
import { Observable } from 'rxjs';
import { TipStudijaRDF } from '../model/TipStudijaRDF.model';

@Injectable({
  providedIn: 'root',
})
export class TipStudijaService {
    private baseUrl = 'http://localhost:8080/api/rdf/tipovistudija';

  constructor(private http: HttpClient) {}

  // Svi aktivni (koristi se van admin panela)
//   getAll(): Observable<string[]> {
//     return this.http.get<string[]>(`${this.baseUrl}`);
//   }

getAll(): Observable<TipStudijaRDF[]> {
  return this.http.get<TipStudijaRDF[]>(`${this.baseUrl}`);
}

pretragaSparql(tekst: string): Observable<TipStudijaRDF[]> {
  return this.http.get<TipStudijaRDF[]>(`${this.baseUrl}/pretraga?query=${encodeURIComponent(tekst)}`);
}

pretragaPoDeleted(query: string): Observable<TipStudijaRDF[]> {
  return this.http.get<TipStudijaRDF[]>(`${this.baseUrl}/pretraga-deleted?query=${query}`);
}




  getAllAdmin(): Observable<TipStudijaRDF[]> {
  return this.http.get<TipStudijaRDF[]>(`${this.baseUrl}/admin/dto`);
}

aktiviraj(tip: string): Observable<void> {
  return this.http.put<void>(`${this.baseUrl}/${encodeURIComponent(tip)}/aktiviraj`, {});
}

deaktiviraj(tip: string): Observable<void> {
  return this.http.put<void>(`${this.baseUrl}/${encodeURIComponent(tip)}/deaktiviraj`, {});
}

  // Svi uključujući i deaktivirane (za admin panel)
  getAllAdmin1(): Observable<string[]> {
    return this.http.get<string[]>(`${this.baseUrl}/admin`);
  }

  // Get tip studija po URI
    getByUri(uri: string): Observable<TipStudijaRDF> {
        const params = new HttpParams().set('uri', uri);
        return this.http.get<TipStudijaRDF>(`${this.baseUrl}/by-uri`, { params });
    }

    // Update po URI
    updateByUri(uri: string, noviTip: string): Observable<void> {
    const params = new HttpParams()
        .set('uri', uri)
        .set('noviTip', noviTip);
    return this.http.put<void>(`${this.baseUrl}/update-by-uri`, null, { params });
    }


    getById(tip: string): Observable<string> {
        // const encodedTip = encodeURIComponent(tip); // << dodaj ovo
        const params = new HttpParams().set('tip', tip);
        // return this.http.get<string>(`${this.baseUrl}/by-naziv`, { params });
         return this.http.get<string>(`${this.baseUrl}/by-naziv`, {
            params,
            responseType: 'text' as 'json'
        });
    }



  // Dodaj novi (POST /?tip=...)
  create(tip: string): Observable<void> {
    const params = new HttpParams().set('tip', tip);
    return this.http.post<void>(`${this.baseUrl}`, null, { params });
  }

  // Izmeni postojeći tip (PUT /?stariTip=...&noviTip=...)
  update(stariTip: string, noviTip: string): Observable<void> {
    const params = new HttpParams()
      .set('stariTip', stariTip)
      .set('noviTip', noviTip);
    return this.http.put<void>(`${this.baseUrl}`, null, { params });
  }
  
//   private baseUrl = 'http://localhost:8080/api/tipovistudija';

//   constructor(private http: HttpClient) {}

//   // Svi aktivni (koristi se van admin panela)
//   getAll(): Observable<TipStudija[]> {
//     return this.http.get<TipStudija[]>(`${this.baseUrl}`);
//   }

//   // Svi uključujući i deaktivirane (za admin panel)
//   getAllAdmin(): Observable<TipStudija[]> {
//     return this.http.get<TipStudija[]>(`${this.baseUrl}/admin`);
//   }

//   // Aktiviraj tip
//   aktiviraj(id: number): Observable<void> {
//     return this.http.patch<void>(`${this.baseUrl}/${id}/aktiviraj`, {});
//   }

//   // Deaktiviraj tip
//   deaktiviraj(id: number): Observable<void> {
//     return this.http.patch<void>(`${this.baseUrl}/${id}/deaktiviraj`, {});
//   }

//   // Dohvati jedan po ID-u
//   getById(id: number): Observable<TipStudija> {
//     return this.http.get<TipStudija>(`${this.baseUrl}/${id}`);
//   }

//   // Dodaj novi
//   create(tip: Partial<TipStudija>): Observable<TipStudija> {
//     return this.http.post<TipStudija>(`${this.baseUrl}`, tip);
//   }

//   // Izmeni postojeći
//   update(id: number, tip: Partial<TipStudija>): Observable<TipStudija> {
//     return this.http.put<TipStudija>(`${this.baseUrl}/${id}`, tip);
//   }

}
