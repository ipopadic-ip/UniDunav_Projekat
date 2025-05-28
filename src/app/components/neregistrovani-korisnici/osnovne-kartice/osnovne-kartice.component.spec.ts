import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OsnovneKarticeComponent } from './osnovne-kartice.component';

describe('OsnovneKarticeComponent', () => {
  let component: OsnovneKarticeComponent;
  let fixture: ComponentFixture<OsnovneKarticeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OsnovneKarticeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OsnovneKarticeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
