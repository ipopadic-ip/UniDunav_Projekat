import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WhyChooseUsComponent } from './why-choose-us.component';

describe('WhyChooseUsComponent', () => {
  let component: WhyChooseUsComponent;
  let fixture: ComponentFixture<WhyChooseUsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [WhyChooseUsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WhyChooseUsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
