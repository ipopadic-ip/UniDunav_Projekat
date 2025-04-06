import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RectorComponent } from './rector.component';

describe('RectorComponent', () => {
  let component: RectorComponent;
  let fixture: ComponentFixture<RectorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RectorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
