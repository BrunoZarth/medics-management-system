import { Component } from '@angular/core';
import { MedicService } from '../medic/medic.service';

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteComponent {


  searchType!: string;
  searchValue: any;
  readyToSearch: boolean = false;
  medicList!: any;

  constructor(private medicService: MedicService) {
  }

  ngOnInit(): void {

  }

  getMedicList() {
    return this.medicList;
  }

  deleteMedic(id: string, name: string) {
    if(confirm("Are you sure to delete "+name+" from database?")) {
      this.medicService.deleteById(id);
      this.readyToSearch = false;
    }
    
  }

}
