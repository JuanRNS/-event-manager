import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalViewPartyComponent } from './modal-view-party.component';

describe('ModalViewPartyComponent', () => {
  let component: ModalViewPartyComponent;
  let fixture: ComponentFixture<ModalViewPartyComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalViewPartyComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalViewPartyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
