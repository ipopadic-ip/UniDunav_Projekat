import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FakultetiComponent } from './fakulteti.component';

describe('FakultetiComponent', () => {
  let component: FakultetiComponent;
  let fixture: ComponentFixture<FakultetiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FakultetiComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FakultetiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
