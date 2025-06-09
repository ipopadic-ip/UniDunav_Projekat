import { StudijskiProgram } from "./studijski-program.model";
export interface GodinaStudija {
  id: number;
  godina: number;
  studijskiProgramId: number;
  studijskiProgramNaziv: string;
  deleted: boolean;
  studijskiProgram?: StudijskiProgram;
}
