export interface PohadjanjePredmetaDTO {
  id?: number;
  ocena: number;
  brojPolaganja: number;
  aktivan: boolean;
  datumPocetka: string;
  datumZavrsetka: string;
  studentId: number;
  predmetId: number;
}