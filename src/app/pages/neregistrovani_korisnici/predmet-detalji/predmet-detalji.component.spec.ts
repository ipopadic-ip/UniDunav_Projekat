import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredmetDetaljiComponent } from './predmet-detalji.component';

describe('PredmetDetaljiComponent', () => {
  let component: PredmetDetaljiComponent;
  let fixture: ComponentFixture<PredmetDetaljiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PredmetDetaljiComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PredmetDetaljiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
