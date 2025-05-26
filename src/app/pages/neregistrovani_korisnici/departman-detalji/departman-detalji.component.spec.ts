import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DepartmanDetaljiComponent } from './departman-detalji.component';

describe('DepartmanDetaljiComponent', () => {
  let component: DepartmanDetaljiComponent;
  let fixture: ComponentFixture<DepartmanDetaljiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DepartmanDetaljiComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DepartmanDetaljiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
