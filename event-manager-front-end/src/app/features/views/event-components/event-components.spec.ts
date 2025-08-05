import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventComponentsComponent } from './event-components.component';

describe('EventComponents', () => {
  let component: EventComponentsComponent;
  let fixture: ComponentFixture<EventComponentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EventComponentsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventComponentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
