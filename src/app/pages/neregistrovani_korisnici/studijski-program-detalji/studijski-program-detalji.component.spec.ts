import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudijskiProgramDetaljiComponent } from './studijski-program-detalji.component';

describe('StudijskiProgramDetaljiComponent', () => {
  let component: StudijskiProgramDetaljiComponent;
  let fixture: ComponentFixture<StudijskiProgramDetaljiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudijskiProgramDetaljiComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudijskiProgramDetaljiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
