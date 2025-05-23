// export interface Dekan {
//     ime: string;
//     titula: string;
//     opis: string;
//     slika: string;
//   }
  
//   export interface Fakultet {
//     id: number;
//     naziv: string;
//     lokacija: string;
//     kontakt: string;
//     telefon: string;
//     opis: string;
//     slika: string;
//     dekan: Dekan;
//   }
  
export interface Fakultet {
  id: number;
  naziv: string;
  lokacija: string;
  email: string;
  opis: string;
  brojTelefona: string;

  dekanIme: string;
  dekanPrezime: string;
  dekanOpis: string;

}
