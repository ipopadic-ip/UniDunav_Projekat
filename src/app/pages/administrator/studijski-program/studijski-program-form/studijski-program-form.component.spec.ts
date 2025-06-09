import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudijskiProgramFormComponent } from './studijski-program-form.component';

describe('StudijskiProgramFormComponent', () => {
  let component: StudijskiProgramFormComponent;
  let fixture: ComponentFixture<StudijskiProgramFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudijskiProgramFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudijskiProgramFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
