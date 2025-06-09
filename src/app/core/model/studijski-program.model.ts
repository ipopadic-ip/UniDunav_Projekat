// export interface Rukovodilac {
//     ime: string;
//     titula: string;
//     opis: string;
//     slika: string;
//   }
  
//   export interface Predmet {
//     naziv: string;
//     silabus: string;
//     materijali: string[];
//   }
  
//   export interface StudijskiProgram {
//     id: number;
//     naziv: string;
//     opis: string;
//     fakultetId: number; 
//     rukovodilac: Rukovodilac;
//     predmeti: Predmet[];
//   }

// import { TipStudija } from './tip-studija.model';
import { TipStudijaRDF } from './TipStudijaRDF.model';
import { Profesor } from './profesor.model';

export interface StudijskiProgram {
  id: number;
  naziv: string;
  opis: string;
  godineStudija: number | null;
  // tipStudija: TipStudija;
  // tipStudija:TipStudijaRDF;
  // tipStudija: { tip: string; uri: string };
  tipStudija: {
    tip: string;
    uri: string;
    deleted?: boolean;
  };
  rukovodilac: Profesor;
  katedraId: number;
  deleted:boolean;
}

  