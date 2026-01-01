import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamsManagement } from './teams-management';

describe('TeamsManagement', () => {
  let component: TeamsManagement;
  let fixture: ComponentFixture<TeamsManagement>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TeamsManagement]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeamsManagement);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
