export interface Dekan {
    ime: string;
    titula: string;
    opis: string;
    slika: string;
  }
  
  export interface Fakultet {
    id: number;
    naziv: string;
    lokacija: string;
    kontakt: string;
    telefon: string;
    opis: string;
    slika?: string;
    dekan: Dekan;
  }
  