import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewDriverFormComponent } from './new-driver-form.component';

describe('NewDriverFormComponent', () => {
  let component: NewDriverFormComponent;
  let fixture: ComponentFixture<NewDriverFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewDriverFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewDriverFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
