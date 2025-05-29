import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPanelDugmeComponent } from './admin-panel-dugme.component';

describe('AdminPanelDugmeComponent', () => {
  let component: AdminPanelDugmeComponent;
  let fixture: ComponentFixture<AdminPanelDugmeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminPanelDugmeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPanelDugmeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
