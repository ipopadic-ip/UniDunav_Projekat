import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FakultetListComponent } from './fakultet-list.component';

describe('FakultetListComponent', () => {
  let component: FakultetListComponent;
  let fixture: ComponentFixture<FakultetListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FakultetListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FakultetListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
