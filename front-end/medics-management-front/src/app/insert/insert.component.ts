import { Component, Input, OnInit, ViewChild, ɵɵtrustConstantResourceUrl } from '@angular/core';
import { AbstractControl, AsyncValidatorFn, FormControl, FormGroup, NgForm, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { MedicService } from '../medic/medic.service';
import { EMedicalSpecialty } from '../medic/emedicalspecialty';
import { Medic } from '../medic/medic';

@Component({
  selector: 'app-insert',
  templateUrl: './insert.component.html',
  styleUrls: ['./insert.component.css']
})
export class InsertComponent implements OnInit {



  formGroup!: FormGroup;
  adress!: any;
  medicalSpecialty: Set<string> = new Set<string>;
  specialListIsValid = false;
  eMedicalSpecialty!: typeof EMedicalSpecialty;

  @Input()
  medic: Medic = new Medic();

  @ViewChild('formDirective')
  private formDirective!: NgForm;

  constructor(private medicService: MedicService) {
    this.medic.medicalSpecialty = [];
  }

  ngOnInit() {
    this.formGroup = new FormGroup({
      name: new FormControl(this.medic.name, [Validators.required, Validators.minLength(3), Validators.maxLength(120)]),
      crm: new FormControl(this.medic.crm, [Validators.required, Validators.minLength(3), Validators.maxLength(7), Validators.pattern(/^[0-9]{6}$/)]),
      landline: new FormControl(this.medic.landline, [Validators.required, Validators.pattern(/^[0-9]{11}$/)]),
      phone: new FormControl(this.medic.phone, [Validators.required, Validators.pattern(/^[0-9]{11}$/)]),
      cep: new FormControl(this.medic.cep, [Validators.required, Validators.pattern(/^[0-9]{8}$/)], [this.cepIsValid()]),
      localidade: new FormControl(''),
      uf: new FormControl(''),
      bairro: new FormControl(''),
      complemento: new FormControl(''),
      ddd: new FormControl(''),
      gia: new FormControl(''),
      ibge: new FormControl(''),
      logradouro: new FormControl(''),
      siafi: new FormControl(''),
      medicalSpecialty: new FormControl(this.medic.medicalSpecialty),
    });

    this.eMedicalSpecialty = EMedicalSpecialty

  }

  submitForm(): boolean {
    if (this.formGroup.valid) {
      let medic: Medic = new Medic();
      medic.adress = this.adress.localidade + "/" + this.adress.uf + ", Bairro: " + this.adress.bairro + ", Logradouro: " + this.adress.logradouro + ", " + this.adress.complemento
      medic.cep = this.formGroup.value.cep
      medic.crm = this.formGroup.value.crm
      //medic.id = this.formGroup.value.id
      medic.landline = this.formGroup.value.landline
      medic.name = this.formGroup.value.name
      medic.phone = this.formGroup.value.phone
      medic.medicalSpecialty = this.generateSpecialtyList()

      if (this.medic.id != null) {
        this.medicService.updateAndResetForm(medic, this.formDirective)
      } else {
        this.medicService.saveAndResetForm(medic, this.formDirective)
      }

      return true
    } else {
      console.log("Invalid form!")
      return false
    }
  }

  specialtyListIsValid() {
    return (control: AbstractControl): Observable<ValidationErrors> | any => {
      if (this.specialListIsValid) return true; else return false
    }
  }

  cepIsValid(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors> | any => {
      if (/^[0-9]{8}$/.test(control.value)) {
        this.adress = this.medicService.getAdressViaCep(control.value).subscribe({
          next: (adress) => { console.log(adress), this.adress = adress },
          error: (e) => { console.error(e) },
          complete: () => console.info("completed get adress via cep")
        });
      }

      return this.validateCep(control.value)
    };
  }

  async validateCep(cep: any) {
    if (!this.adress.erro) {
      return true
    } else {
      return false
    }
  }

  getLocalidade() {
    return this.adress.localidade
  }

  addOrRemoveFromSpecialtySet(check: string) {
    console.log(check + "começou checkagem")
    if (this.medicalSpecialty.has(check)) {
      this.medicalSpecialty.delete(check)
      if (this.medic.medicalSpecialty.includes(check)) {
        let index = this.medic.medicalSpecialty.indexOf(check)
        this.medic.medicalSpecialty.splice(index, 1);
      }
    } else this.medicalSpecialty.add(check)

    console.log("this.medicalSpecialty" + this.medicalSpecialty)
    console.log("this.medic.medicalSpecialty" + this.medic.medicalSpecialty)

    if (this.medicalSpecialty.size >= 2) {
      this.specialListIsValid = true;
      console.log("list must be true :" + this.specialListIsValid)
    } else {
      this.specialListIsValid = false;
      console.log("list must be false :" + this.specialListIsValid)
    }
  }

  verifyIfIsOnSpecialtyList(specialty: string) {
    if (!this.isUpdateForm) return false; else
      if (this.medic.medicalSpecialty.includes(specialty)) {
        this.medicalSpecialty.add(specialty)
        if (this.medicalSpecialty.size >= 2) this.specialListIsValid = true

        return true
      } else return false
  }

  isUpdateForm() {
    if (this.medic.id != null) {
      return true
    } else {
      return false
    }
  }

  generateSpecialtyList(): string[] {
    let medicalSpecialtySet = new Set<string>()
    let medicalSpecialtyArray = new Array<string>()
    this.medicalSpecialty.forEach((m) => medicalSpecialtySet.add(m))
    this.medic.medicalSpecialty.forEach((m) => medicalSpecialtySet.add(m))
    medicalSpecialtySet.forEach((m) => medicalSpecialtyArray.push(m))
    return medicalSpecialtyArray
  }
}