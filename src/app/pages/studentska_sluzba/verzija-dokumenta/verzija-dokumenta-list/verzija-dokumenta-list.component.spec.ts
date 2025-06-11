import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerzijaDokumentaListComponent } from './verzija-dokumenta-list.component';

describe('VerzijaDokumentaListComponent', () => {
  let component: VerzijaDokumentaListComponent;
  let fixture: ComponentFixture<VerzijaDokumentaListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VerzijaDokumentaListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VerzijaDokumentaListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
