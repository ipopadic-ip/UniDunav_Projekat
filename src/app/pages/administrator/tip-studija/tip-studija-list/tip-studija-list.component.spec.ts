import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TipStudijaListComponent } from './tip-studija-list.component';

describe('TipStudijaListComponent', () => {
  let component: TipStudijaListComponent;
  let fixture: ComponentFixture<TipStudijaListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TipStudijaListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TipStudijaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
