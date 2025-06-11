import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentProfilComponent } from './student-profil.component';

describe('StudentProfilComponent', () => {
  let component: StudentProfilComponent;
  let fixture: ComponentFixture<StudentProfilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudentProfilComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StudentProfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
