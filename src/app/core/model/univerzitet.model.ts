export interface Rektor {
    id: number;
    ime: string;
    prezime: string;
    biografija: string;
    slikaPath: string;
  }
  
  
  export interface Univerzitet {
  id: number;
  naziv: string;
  podnaslov: string;
  email: string;
  brojTelefona: string;
  lokacija: string;
  opis: string;
  slika1Path: string;
  slika2Path: string;
  rektor: Rektor;
}

