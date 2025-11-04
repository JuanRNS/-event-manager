import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalValuesEmployeeComponent } from './modal-values-employee.component';

describe('ModalValuesEmployeeComponent', () => {
  let component: ModalValuesEmployeeComponent;
  let fixture: ComponentFixture<ModalValuesEmployeeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalValuesEmployeeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalValuesEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
