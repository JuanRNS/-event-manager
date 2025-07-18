import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartyRegistration } from './party-registration';

describe('PartyRegistration', () => {
  let component: PartyRegistration;
  let fixture: ComponentFixture<PartyRegistration>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PartyRegistration]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PartyRegistration);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
