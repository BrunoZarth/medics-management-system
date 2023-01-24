import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicComponent } from './medic.component';

describe('MedicComponent', () => {
  let component: MedicComponent;
  let fixture: ComponentFixture<MedicComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MedicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
