import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLoanComponent } from './add-loan.component';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';

describe('AddLoanComponent', () => {
  let component: AddLoanComponent;
  let fixture: ComponentFixture<AddLoanComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule,FormsModule,HttpClient],
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
