import { Component, OnInit } from '@angular/core';
import { takeUntil, takeWhile } from 'rxjs';
import { Medic } from '../medic/medic';
import { MedicService } from '../medic/medic.service';
import { FilterComponent } from '../shared/filter/filter.component';

@Component({
  selector: 'app-select',
  templateUrl: './select.component.html',
  styleUrls: ['./select.component.css']
})
export class SelectComponent implements OnInit {

  searchType!: string;
  searchValue: any;
  readyToSearch: boolean = false;
  medicList!: any;

  constructor() {
  }

  ngOnInit(): void {

  }

  getMedicList() {
    return this.medicList;
  }
}
