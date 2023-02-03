import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { DeleteComponent } from 'src/app/delete/delete.component';
import { MedicService } from 'src/app/medic/medic.service';
import { SelectComponent } from 'src/app/select/select.component';
import { UpdateComponent } from 'src/app/update/update.component';


@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.css']
})
export class FilterComponent implements OnInit {

  searchType!: string;
  searchValue!: any;
  readyToSearch: boolean = false;

  @Input()
  component!: string;

  formFilter!: FormGroup;
  medicList: any;
  sub: any;

  constructor(private medicService: MedicService, private selectComponent: SelectComponent, private deleteComponent: DeleteComponent, private updateComponent: UpdateComponent) {

  }
  
  ngOnInit(): void {
    this.formFilter = new FormGroup({
      searchType: new FormControl(this.searchType),
      searchValue: new FormControl(this.searchValue)
    })
  }

  storeFilters() {
    console.log("store filters init")
    console.log(this.formFilter.value.searchType)
    console.log(this.formFilter.value.searchValue)

    if (!(this.formFilter.value.searchType == null) && !(this.formFilter.value.searchValue == null) || this.formFilter.value.searchType == "find all") {
      console.log("tudo ok")
      this.searchType = this.formFilter.value.searchType;
      this.searchValue = this.formFilter.value.searchValue;
      console.log(this.searchType)
      console.log(this.searchValue)
      this.readyToSearch = true
      this.getMedicService();


    } else {
      console.log("incorrect values!")
      this.readyToSearch = false
      this.updateComponents();
    }
    this.formFilter.reset
  }

  updateComponents() {
    console.log("update components")

    if (this.component == "select") {
      this.selectComponent.searchType = this.searchType
      this.selectComponent.searchValue = this.searchValue
      this.selectComponent.readyToSearch = false
      this.selectComponent.medicList = this.medicList
      this.selectComponent.readyToSearch = this.readyToSearch
    }

    if (this.component == "delete") {
      this.deleteComponent.searchType = this.searchType
      this.deleteComponent.searchValue = this.searchValue
      this.deleteComponent.readyToSearch = false
      this.deleteComponent.medicList = this.medicList
      this.deleteComponent.readyToSearch = this.readyToSearch
    }

    if (this.component == "update") {
      this.updateComponent.searchType = this.searchType
      this.updateComponent.searchValue = this.searchValue
      this.updateComponent.readyToSearch = false
      this.updateComponent.medicList = this.medicList
      this.updateComponent.readyToSearch = this.readyToSearch
    }
  }

  getMedicService() {
    if (!(this.searchType == null) && !(this.searchValue == null) || this.searchType == "find all") {
      if (this.searchType == "find all") {
        this.sub = this.medicService.findAll().subscribe({
          next: (data) => {
            this.medicList = data;
          }, error: (e) => {
            console.log(e)
            this.selectComponent.readyToSearch = false
          }, complete: () => {
            this.updateComponents();
          }
        });
      }
      if (this.searchType == "id") {
        this.sub = this.medicService.findById(this.searchValue).subscribe({
          next: (data) => {
            this.medicList = []
            this.medicList[0] = data;
          }, error: (e) => {
            console.log(e)
            this.selectComponent.readyToSearch = false
          }, complete: () => {
            this.updateComponents();
          }
        });
      }
      if (this.searchType == "crm") {
        this.sub = this.medicService.findByCrm(this.searchValue).subscribe({
          next: (data) => {
            this.medicList = []
            this.medicList[0] = data;
          }, error: (e) => {
            console.log(e)
            this.selectComponent.readyToSearch = false
          }, complete: () => {
            this.updateComponents();
          }
        });
      }
      if (this.searchType == "name") {
        this.sub = this.medicService.findByName(this.searchValue).subscribe({
          next: (data) => {
            this.medicList = data;
          }, error: (e) => {
            console.log(e)
            this.selectComponent.readyToSearch = false
          }, complete: () => {
            this.updateComponents();
          }
        });
      }
      if (this.searchType == "landline") {
        this.sub = this.medicService.findByLandline(this.searchValue).subscribe({
          next: (data) => {
            this.medicList = data;
          }, error: (e) => {
            console.log(e)
            this.selectComponent.readyToSearch = false
          }, complete: () => {
            this.updateComponents();
          }
        });
      }
      if (this.searchType == "phone") {
        this.sub = this.medicService.findByPhone(this.searchValue).subscribe({
          next: (data) => {
            this.medicList = []
            this.medicList[0] = data;
          }, error: (e) => {
            console.log(e)
            this.selectComponent.readyToSearch = false
          }, complete: () => {
            this.updateComponents();
          }
        });
      }
      if (this.searchType == "cep") {
        this.sub = this.medicService.findByCep(this.searchValue).subscribe({
          next: (data) => {
            this.medicList = data;
          }, error: (e) => {
            console.log(e)
            this.selectComponent.readyToSearch = false
          }, complete: () => {
            this.updateComponents();
          }
        });
      }
      if (this.searchType == "specialty") {
        this.sub = this.medicService.findBySpecialty(this.searchValue).subscribe({
          next: (data) => {
            this.medicList = data;
          }, error: (e) => {
            console.log(e)
            this.selectComponent.readyToSearch = false
          }, complete: () => {
            this.updateComponents();
          }
        });
      }
    }
  }
}
