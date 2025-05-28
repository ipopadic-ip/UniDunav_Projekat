import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KatedraDetaljiComponent } from './katedra-detalji.component';

describe('KatedraDetaljiComponent', () => {
  let component: KatedraDetaljiComponent;
  let fixture: ComponentFixture<KatedraDetaljiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KatedraDetaljiComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KatedraDetaljiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
