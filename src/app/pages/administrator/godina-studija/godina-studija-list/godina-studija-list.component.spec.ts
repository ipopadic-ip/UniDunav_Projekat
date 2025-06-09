import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GodinaStudijaListComponent } from './godina-studija-list.component';

describe('GodinaStudijaListComponent', () => {
  let component: GodinaStudijaListComponent;
  let fixture: ComponentFixture<GodinaStudijaListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GodinaStudijaListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GodinaStudijaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
