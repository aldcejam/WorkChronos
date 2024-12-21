package idus.api.workchronos.domain.workManagment;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Getter
public class AttendanceRecord {
    private final UUID id;
    private final UUID userId;
    private final LocalDate workDate;
    private final WorkEntrie entrie;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private AttendanceRecord(UUID id, UUID userId, LocalDate workDate, WorkEntrie entrie, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.workDate = workDate;
        this.entrie = entrie;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static AttendanceRecord startDay (UUID userId, AttendanceRecord latestRecord) {
        WorkEntrie latestEntrie = latestRecord != null ? latestRecord.getEntrie() : null;
        if (latestEntrie != null && latestEntrie.isWorking()) throw new RuntimeException("User is already working");

        WorkEntrie workEntries = WorkEntrie.startWork(LocalTime.now());
        LocalDate workDate = LocalDate.now();
        return new AttendanceRecord(null, userId, workDate, workEntries, null, null);
    }

    public AttendanceRecord finishDay() {
        if (!this.entrie.isWorking()) throw new RuntimeException("User is not working");
        this.entrie.finishWork(LocalTime.now());
        return this;
    }

    public static AttendanceRecord with(UUID id, UUID userId, LocalDate workDate, WorkEntrie entrie, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new AttendanceRecord(id, userId, workDate, entrie, createdAt, updatedAt);
    }

    public static AttendanceRecord with(UUID id, UUID userId, LocalDate workDate, String entrie, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new AttendanceRecord(id, userId, workDate, WorkEntrie.fromString(entrie), createdAt, updatedAt);
    }
}
