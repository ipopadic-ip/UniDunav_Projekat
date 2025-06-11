import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EksportComponent } from './eksport.component';

describe('EksportComponent', () => {
  let component: EksportComponent;
  let fixture: ComponentFixture<EksportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EksportComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EksportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
