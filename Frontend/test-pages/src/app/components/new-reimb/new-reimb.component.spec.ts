import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewReimbComponent } from './new-reimb.component';

describe('NewReimbComponent', () => {
  let component: NewReimbComponent;
  let fixture: ComponentFixture<NewReimbComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewReimbComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewReimbComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
