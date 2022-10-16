import { TestBed } from '@angular/core/testing';

import { ReimbursementsServiceTsService } from './reimbursements.service.ts.service';

describe('ReimbursementsServiceTsService', () => {
  let service: ReimbursementsServiceTsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ReimbursementsServiceTsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
