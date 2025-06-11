import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DokumentFormComponent } from './dokument-form.component';

describe('DokumentFormComponent', () => {
  let component: DokumentFormComponent;
  let fixture: ComponentFixture<DokumentFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DokumentFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DokumentFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
