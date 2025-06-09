import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfesorPredmetFormComponent } from './profesor-predmet-form.component';

describe('ProfesorPredmetFormComponent', () => {
  let component: ProfesorPredmetFormComponent;
  let fixture: ComponentFixture<ProfesorPredmetFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfesorPredmetFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfesorPredmetFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
