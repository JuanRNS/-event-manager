import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePageSideComponent } from './home-page-side.component';

describe('HomePageSideComponent', () => {
  let component: HomePageSideComponent;
  let fixture: ComponentFixture<HomePageSideComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomePageSideComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HomePageSideComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
