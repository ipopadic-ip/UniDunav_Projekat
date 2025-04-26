import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NastavnikDashboardComponent } from './nastavnik-dashboard.component';

describe('NastavnikDashboardComponent', () => {
  let component: NastavnikDashboardComponent;
  let fixture: ComponentFixture<NastavnikDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NastavnikDashboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NastavnikDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
