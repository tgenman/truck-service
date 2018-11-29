import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminCommonComponent } from './admin-common.component';

describe('AdminCommonComponent', () => {
  let component: AdminCommonComponent;
  let fixture: ComponentFixture<AdminCommonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminCommonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminCommonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
