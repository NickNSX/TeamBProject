import { TestBed } from '@angular/core/testing';

import { ServicesReimbursementsService } from './services.reimbursements.service';

describe('ServicesReimbursementsService', () => {
  let service: ServicesReimbursementsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServicesReimbursementsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
