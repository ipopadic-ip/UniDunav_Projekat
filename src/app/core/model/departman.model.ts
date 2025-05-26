export interface Departman {
  id: number;
  naziv: string;
  opis: string;
  katedre: any[]; // Ako kasnije definišeš model za Katedru, možeš umesto `any` staviti `Katedra[]`
  fakultet: Fakultet;
  sefDepartmana: Profesor; // možeš ga nazvati i Korisnik ako svi idu u isti model
}

export interface Fakultet {
  id: number;
  naziv: string;
  email: string;
  univerzitet: any; // Ako budeš koristio univerzitet DTO kasnije
  dekan: any;       // Isto važi i za dekana
  departmani: any;
  opis: string;
  lokacija: string;
  brojTelefona: string;
}

export interface Profesor {
  id: number;
  ime: string;
  prezime: string;
  biografija: string;
  slikaPath: string;
}
