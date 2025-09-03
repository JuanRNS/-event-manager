import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalViewGarcomComponent } from './modal-view-garcom.component';

describe('ModalViewGarcomComponent', () => {
  let component: ModalViewGarcomComponent;
  let fixture: ComponentFixture<ModalViewGarcomComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalViewGarcomComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalViewGarcomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
