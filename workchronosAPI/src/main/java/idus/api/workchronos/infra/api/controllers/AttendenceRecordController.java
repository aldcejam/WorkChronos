package idus.api.workchronos.infra.api.controllers;

import idus.api.workchronos.application.AttendenceRecordService;
import idus.api.workchronos.domain.workManagment.AttendanceRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController()
@RequestMapping("/attendance-record")
public class AttendenceRecordController {

    AttendenceRecordService attendenceRecordService;

    public AttendenceRecordController(AttendenceRecordService attendenceRecordService) {
        this.attendenceRecordService = attendenceRecordService;
    }

    @PostMapping("/{userId}/start-day")
    public ResponseEntity<AttendanceRecord> startDay(@PathVariable UUID userId) {
        AttendanceRecord record = this.attendenceRecordService.startDay(userId);
        return ResponseEntity.ok(record);
    }

    @PostMapping("/{userId}/finish-day")
    public ResponseEntity<AttendanceRecord> finishDay(@PathVariable UUID userId) {
        AttendanceRecord record = this.attendenceRecordService.finishDay(userId);
        return ResponseEntity.ok(record);
    }

    @PostMapping("/{userId}/start-break")
    public ResponseEntity<AttendanceRecord> startBreak(@PathVariable UUID userId) {
        AttendanceRecord record = this.attendenceRecordService.startBreak(userId);
        return ResponseEntity.ok(record);
    }

    @PostMapping("/{userId}/finish-break")
    public ResponseEntity<AttendanceRecord> finishBreak(@PathVariable UUID userId) {
        AttendanceRecord record = this.attendenceRecordService.finishBreak(userId);
        return ResponseEntity.ok(record);
    }
}
