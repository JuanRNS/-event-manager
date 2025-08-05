import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartyRegistrationComponent } from './party-registration.component';

describe('PartyRegistration', () => {
  let component: PartyRegistrationComponent;
  let fixture: ComponentFixture<PartyRegistrationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PartyRegistrationComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PartyRegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
