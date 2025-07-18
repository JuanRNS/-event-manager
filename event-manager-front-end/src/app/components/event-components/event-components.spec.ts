import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EventComponents } from './event-components';

describe('EventComponents', () => {
  let component: EventComponents;
  let fixture: ComponentFixture<EventComponents>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EventComponents]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EventComponents);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
