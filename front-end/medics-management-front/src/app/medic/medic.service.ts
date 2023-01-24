import { Injectable } from '@angular/core';
import { HttpClient, HttpHandler, HttpHeaders } from "@angular/common/http"
import { Medic } from './medic';
import { EMedicalSpecialty } from './emedicalspecialty';
import { MedicToSave } from './medictosave';

@Injectable({
  providedIn: 'root'
})
export class MedicService {

  //private url = "http://localhost:8080/medic/";
  private url = "http://localhost:4200/api/medic/";
  private httpOptions = {
    Headers: new HttpHeaders({'content-type': 'application/json'})
  }
  public valid:boolean = false;
  public errorMsg:any;

  constructor(private http:HttpClient) { }

  // READ
  findById(id: string){
    let newUrl = this.url.concat("find-by-id/" + id);
    return this.http.get<Medic>(newUrl)
  };

  findByPhone(phone: string){
    let newUrl = this.url.concat("find-by-phone/" + phone);
    return this.http.get(newUrl)
  };

  findByCrm(crm: string){
    let newUrl = this.url.concat("find-by-crm/" + crm);
    return this.http.get(newUrl)
  };

  findAll(){
    let newUrl = this.url.concat("find-all");
    let response = this.http.get(newUrl)
    return response
  };

  findByLandline(landline: string){
    let newUrl = this.url.concat("find-by-landline/" + landline);
    return this.http.get(newUrl)
  };

  findByName(name: string){
    let newUrl = this.url.concat("find-by-name/" + name);
    return this.http.get(newUrl)
  };

  findByCep(cep: string){
    let newUrl = this.url.concat("find-by-cep/" + cep);
    return this.http.get(newUrl)
  };

  findBySpecialty(medicalSpecialty: EMedicalSpecialty){
    let newUrl = this.url.concat("find-by-specialty/" + medicalSpecialty);
    return this.http.get(newUrl)
  };

  // CREATE
  save(medictosave: MedicToSave){
    let newUrl = this.url.concat("save");
    return this.http.post<Medic>(newUrl, medictosave).subscribe({
      next: (m:Medic) => {console.log(m), this.valid = true},
      error: (e) => {console.error(e), this.valid = false, this.errorMsg = e.message},
      complete: () => {console.info('complete')}
    })
  };

  // UPDATE
  update(medic: Medic){

    console.log("update medic ok");
    console.log(medic);

    let newUrl = this.url.concat("update");

    return this.http.put<Medic>(newUrl, medic).subscribe({
      next: (m:Medic) => {console.log(m), this.valid = true},
      error: (e) => {console.error(e), this.valid = false, this.errorMsg = e.message},
      complete: () => {console.info('updated')}
    })
  };

  // DELETE
  deleteById(id: string){
    let newUrl = this.url.concat("delete-by-id/" + id);

    this.http.delete(newUrl).subscribe({
      next: () => {console.log(), this.valid=true},
      error: (e) => {console.error(e), this.valid = false, this.errorMsg = e.message},
      complete: () => console.info('medic id: ' + id + ' removed')
    })
  };



}
