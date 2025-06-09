import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudijskiProgramListComponent } from './studijski-program-list.component';

describe('StudijskiProgramListComponent', () => {
  let component: StudijskiProgramListComponent;
  let fixture: ComponentFixture<StudijskiProgramListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudijskiProgramListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudijskiProgramListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
