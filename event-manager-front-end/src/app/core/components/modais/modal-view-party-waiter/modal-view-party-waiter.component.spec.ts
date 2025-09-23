import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalViewPartyWaiterComponent } from './modal-view-party-waiter.component';

describe('ModalViewPartyWaiterComponent', () => {
  let component: ModalViewPartyWaiterComponent;
  let fixture: ComponentFixture<ModalViewPartyWaiterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalViewPartyWaiterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalViewPartyWaiterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
