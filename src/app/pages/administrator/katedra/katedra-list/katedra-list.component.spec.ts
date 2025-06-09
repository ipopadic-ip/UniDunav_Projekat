import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KatedraListComponent } from './katedra-list.component';

describe('KatedraListComponent', () => {
  let component: KatedraListComponent;
  let fixture: ComponentFixture<KatedraListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [KatedraListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KatedraListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
