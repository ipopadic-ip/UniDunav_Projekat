import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SluzbenikDashboardComponent } from './sluzbenik-dashboard.component';

describe('SluzbenikDashboardComponent', () => {
  let component: SluzbenikDashboardComponent;
  let fixture: ComponentFixture<SluzbenikDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SluzbenikDashboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SluzbenikDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
