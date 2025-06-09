import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TipStudijaFormComponent } from './tip-studija-form.component';

describe('TipStudijaFormComponent', () => {
  let component: TipStudijaFormComponent;
  let fixture: ComponentFixture<TipStudijaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TipStudijaFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TipStudijaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
