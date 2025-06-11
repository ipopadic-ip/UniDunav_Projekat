import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerzijaDokumentaFormComponent } from './verzija-dokumenta-form.component';

describe('VerzijaDokumentaFormComponent', () => {
  let component: VerzijaDokumentaFormComponent;
  let fixture: ComponentFixture<VerzijaDokumentaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VerzijaDokumentaFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VerzijaDokumentaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
