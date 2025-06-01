export interface PrijavaIspitaDTO {
  id: number;
  datumPrijave: string | null;
  datumIspita: string;
  status: boolean;
  pohadjanjeId: number;
  predmetNaziv: string; 
}