import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PredmetListComponent } from './predmet-list.component';

describe('PredmetListComponent', () => {
  let component: PredmetListComponent;
  let fixture: ComponentFixture<PredmetListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PredmetListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PredmetListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
