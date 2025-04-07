export interface Rektor {
    ime: string;
    titula: string;
    opis: string;
    slika: string;
  }
  
  export interface Univerzitet {
    id: number;
    naziv: string;
    lokacija: string;
    kontakt: string;
    telefon: string;
    opis: string;
    slika: string;
    rektor: Rektor;
  }
  
