import { TestBed } from '@angular/core/testing';

import { ReimbursementServiceTsService } from './reimbursement.service.ts.service';

describe('ReimbursementServiceTsService', () => {
  let service: ReimbursementServiceTsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReimbursementServiceTsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
