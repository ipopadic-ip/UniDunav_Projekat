import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FakultetFormComponent } from './fakultet-form.component';

describe('FakultetFormComponent', () => {
  let component: FakultetFormComponent;
  let fixture: ComponentFixture<FakultetFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FakultetFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FakultetFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
