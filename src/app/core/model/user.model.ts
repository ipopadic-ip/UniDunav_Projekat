export interface Role {
  naziv: string;
}

export interface CreateUserRequest {
  email: string;
  password: string;
  ime: string;
  prezime: string;
  adresa: string;
  jmbg: string;
  roles: Role[];
}
