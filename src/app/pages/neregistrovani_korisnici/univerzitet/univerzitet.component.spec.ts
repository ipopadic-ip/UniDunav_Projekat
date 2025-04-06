import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UniverzitetComponent } from './univerzitet.component';

describe('UniverzitetComponent', () => {
  let component: UniverzitetComponent;
  let fixture: ComponentFixture<UniverzitetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UniverzitetComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UniverzitetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
