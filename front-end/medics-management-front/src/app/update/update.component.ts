import { Component } from '@angular/core';
import { Medic } from '../medic/medic';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent {

  searchType!: string;
  searchValue: any;
  medicList: any;
  medic!: Medic;
  readyToUpdate: boolean = false;
  readyToSearch: any;
getMedicList(): any {
  return this.medicList;
}
updateMedic(medic: any) {
  this.medic = medic
  this.readyToUpdate = true;
}
reloadPage() {
  window.location.reload()
}

}
