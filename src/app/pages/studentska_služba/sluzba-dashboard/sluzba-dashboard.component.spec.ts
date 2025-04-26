import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SluzbaDashboardComponent } from './sluzba-dashboard.component';

describe('SluzbaDashboardComponent', () => {
  let component: SluzbaDashboardComponent;
  let fixture: ComponentFixture<SluzbaDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SluzbaDashboardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SluzbaDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
