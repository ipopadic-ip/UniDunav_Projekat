export interface Role {
  // naziv: string;
  id?: number;         // Dodaj id jer backend vraća i id
  naziv: string;
  aktivna?: boolean;
}