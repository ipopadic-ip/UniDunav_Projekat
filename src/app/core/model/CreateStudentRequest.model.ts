
import { CreateUserRequest } from './CreateUserRequest.model';

export interface CreateStudentRequest {
  brojIndeksa: string;
  godinaUpisa: number;
  user: CreateUserRequest;
}