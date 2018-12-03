import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewManagerFormComponent } from './new-manager-form.component';

describe('NewManagerFormComponent', () => {
  let component: NewManagerFormComponent;
  let fixture: ComponentFixture<NewManagerFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewManagerFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewManagerFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
