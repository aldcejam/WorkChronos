import { TestBed } from '@angular/core/testing';

import { GetAttendanceRecordByUserIdService } from './get-attendance-record-by-user-id.service';

describe('GetAttendanceRecordByUserIdService', () => {
  let service: GetAttendanceRecordByUserIdService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetAttendanceRecordByUserIdService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
