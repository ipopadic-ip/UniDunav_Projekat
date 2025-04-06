import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudijskiProgramiComponent } from './studijski-programi.component';

describe('StudijskiProgramiComponent', () => {
  let component: StudijskiProgramiComponent;
  let fixture: ComponentFixture<StudijskiProgramiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudijskiProgramiComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudijskiProgramiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
