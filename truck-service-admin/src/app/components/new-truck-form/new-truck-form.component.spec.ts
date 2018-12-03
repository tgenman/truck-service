import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewTruckFormComponent } from './new-truck-form.component';

describe('NewTruckFormComponent', () => {
  let component: NewTruckFormComponent;
  let fixture: ComponentFixture<NewTruckFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewTruckFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewTruckFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
