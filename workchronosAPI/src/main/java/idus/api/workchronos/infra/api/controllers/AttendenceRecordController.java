package idus.api.workchronos.infra.api.controllers;

import idus.api.workchronos.application.AttendenceRecordService;
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
    public void startDay(@PathVariable UUID userId) {
        this.attendenceRecordService.startDay(userId);
    }

    @PostMapping("/{userId}/finish-day")
    public void finishDay(@PathVariable UUID userId) {
        this.attendenceRecordService.finishDay(userId);
    }
}
