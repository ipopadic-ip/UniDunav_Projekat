import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SluzbenikProfilComponent } from './sluzbenik-profil.component';

describe('SluzbenikProfilComponent', () => {
  let component: SluzbenikProfilComponent;
  let fixture: ComponentFixture<SluzbenikProfilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SluzbenikProfilComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SluzbenikProfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
