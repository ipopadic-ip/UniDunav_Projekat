export interface Role {
  id?: number;
  naziv: string;
  aktivna?: boolean;
}

export interface CreateUserRequest {
  id?: number;
  email: string;
  password: string;
  ime: string;
  prezime: string;
  adresa: string;
  jmbg: string;
  roles: Role[];
}
