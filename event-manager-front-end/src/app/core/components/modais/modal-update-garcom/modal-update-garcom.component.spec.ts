import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalUpdateGarcomComponent } from './modal-update-garcom.component';

describe('ModalUpdateGarcomComponent', () => {
  let component: ModalUpdateGarcomComponent;
  let fixture: ComponentFixture<ModalUpdateGarcomComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalUpdateGarcomComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalUpdateGarcomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
