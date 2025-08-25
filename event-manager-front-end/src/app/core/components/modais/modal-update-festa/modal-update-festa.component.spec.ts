import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalUpdateFestaComponent } from './modal-update-festa.component';

describe('ModalUpdateFestaComponent', () => {
  let component: ModalUpdateFestaComponent;
  let fixture: ComponentFixture<ModalUpdateFestaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalUpdateFestaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalUpdateFestaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
