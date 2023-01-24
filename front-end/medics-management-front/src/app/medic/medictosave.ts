import { EMedicalSpecialty } from "./emedicalspecialty";

export class MedicToSave{
    name!: string; 
    crm!: string;
    landline!: string; 
    phone!: string; 
    cep!: string; 
    adress!: string; 
    medicalSpecialty!: Array<EMedicalSpecialty>;
}