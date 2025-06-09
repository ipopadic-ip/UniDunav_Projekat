import { Profesor } from './profesor.model';
import { Departman } from './departman.model';

export interface Katedra {
  id: number;
  naziv: string;
  opis: string;
  sefKatedre: Profesor;
  departman: Departman;
  deleted?: boolean;
}
