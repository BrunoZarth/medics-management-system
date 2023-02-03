import { EMedicalSpecialty } from "./emedicalspecialty";

export class Medic{
    id!: number;
    name!: string; 
    crm!: string;
    landline!: string; 
    phone!: string; 
    cep!: string; 
    adress!: string; 
    medicalSpecialty!: Array<string>;
}