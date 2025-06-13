import { Dokument } from "./dokument.model";
export interface VerzijaDokumenta {
  id: number;
  brojVerzije: number;
  autor: string;
  datumKreiranja: string;
  putanjaDoFajla?: string;
  sadrzaj?: string;
  deleted: boolean;
  dokumentId: number;
  dokument: Dokument;
  urlFajl?: string;
}