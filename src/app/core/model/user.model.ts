import { Role } from './role.model';

export interface User {
  id: number;
  email: string;
  ime: string;
  prezime: string;
  adresa: string;
  jmbg: string;
  roles: Role[];
  deleted:boolean;
}