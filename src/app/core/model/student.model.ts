import { User } from './user.model';

export interface Student {
  id: number;
  user: User;
  brojIndeksa: string;
  godinaUpisa: number;
  prosecnaOcena: number;
  ukupnoEcts: number;
}