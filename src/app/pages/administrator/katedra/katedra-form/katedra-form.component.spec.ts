import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KatedraFormComponent } from './katedra-form.component';

describe('KatedraFormComponent', () => {
  let component: KatedraFormComponent;
  let fixture: ComponentFixture<KatedraFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KatedraFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KatedraFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
