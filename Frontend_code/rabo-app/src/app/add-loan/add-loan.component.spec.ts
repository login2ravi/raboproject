import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLoanComponent } from './add-loan.component';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';

describe('AddLoanComponent', () => {
  let component: AddLoanComponent;
  let fixture: ComponentFixture<AddLoanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule,FormsModule],
      declarations: [ AddLoanComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddLoanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
