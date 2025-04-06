import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RukovodilacComponent } from './rukovodilac.component';

describe('RukovodilacComponent', () => {
  let component: RukovodilacComponent;
  let fixture: ComponentFixture<RukovodilacComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RukovodilacComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RukovodilacComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
