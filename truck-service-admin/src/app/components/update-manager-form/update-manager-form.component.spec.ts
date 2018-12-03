import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateManagerFormComponent } from './update-manager-form.component';

describe('UpdateManagerFormComponent', () => {
  let component: UpdateManagerFormComponent;
  let fixture: ComponentFixture<UpdateManagerFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UpdateManagerFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateManagerFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
