import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfesorPredmetListComponent } from './profesor-predmet-list.component';

describe('ProfesorPredmetListComponent', () => {
  let component: ProfesorPredmetListComponent;
  let fixture: ComponentFixture<ProfesorPredmetListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfesorPredmetListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProfesorPredmetListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
