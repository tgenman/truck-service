import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateTruckFormComponent } from './update-truck-form.component';

describe('UpdateTruckFormComponent', () => {
  let component: UpdateTruckFormComponent;
  let fixture: ComponentFixture<UpdateTruckFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateTruckFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateTruckFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
