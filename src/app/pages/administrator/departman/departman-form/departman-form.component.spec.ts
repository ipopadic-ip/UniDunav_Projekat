import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmanFormComponent } from './departman-form.component';

describe('DepartmanFormComponent', () => {
  let component: DepartmanFormComponent;
  let fixture: ComponentFixture<DepartmanFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DepartmanFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DepartmanFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
