import { ComponentFixture, TestBed } from '@angular/core/testing';
import { StudentPanelDugmeComponent } from './student-panel-dugme.component';
import { RouterTestingModule } from '@angular/router/testing';

describe('StudentPanelDugmeComponent', () => {
  let component: StudentPanelDugmeComponent;
  let fixture: ComponentFixture<StudentPanelDugmeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RouterTestingModule],
      declarations: [StudentPanelDugmeComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(StudentPanelDugmeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
