import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredmetFormComponent } from './predmet-form.component';

describe('PredmetFormComponent', () => {
  let component: PredmetFormComponent;
  let fixture: ComponentFixture<PredmetFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PredmetFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PredmetFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
