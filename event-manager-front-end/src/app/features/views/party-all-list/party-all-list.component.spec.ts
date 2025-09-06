import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartyAllListComponent } from './party-all-list.component';

describe('PartyAllListComponent', () => {
  let component: PartyAllListComponent;
  let fixture: ComponentFixture<PartyAllListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PartyAllListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PartyAllListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
