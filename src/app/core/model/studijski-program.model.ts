export interface Rukovodilac {
    ime: string;
    titula: string;
    opis: string;
    slika: string;
  }
  
  export interface Predmet {
    naziv: string;
    silabus: string; // URL do PDF-a
    materijali: string[]; // URL-ovi do fajlova
  }
  
  export interface StudijskiProgram {
    id: number;
    naziv: string;
    opis: string;
    rukovodilac: Rukovodilac;
    predmeti: Predmet[];
  }
  