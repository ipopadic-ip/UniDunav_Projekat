import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmanListComponent } from './departman-list.component';

describe('DepartmanListComponent', () => {
  let component: DepartmanListComponent;
  let fixture: ComponentFixture<DepartmanListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DepartmanListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DepartmanListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
