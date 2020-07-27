import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { LoanService } from './loan.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';

describe('LoanService', () => {
  let service: LoanService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule ],
      declarations: [  ],
      providers: []
    });
    service = TestBed.inject(LoanService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();   
  });
});
