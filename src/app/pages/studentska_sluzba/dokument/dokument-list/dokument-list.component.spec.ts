import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DokumentListComponent } from './dokument-list.component';

describe('DokumentListComponent', () => {
  let component: DokumentListComponent;
  let fixture: ComponentFixture<DokumentListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DokumentListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DokumentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
