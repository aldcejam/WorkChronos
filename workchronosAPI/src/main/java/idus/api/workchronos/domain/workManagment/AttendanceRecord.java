package idus.api.workchronos.domain.workManagment;

import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class AttendanceRecord {
    private final UUID id;
    private final UUID userId;
    private final LocalDate workDate;
    private final WorkEntrie entrie;

    private AttendanceRecord(UUID id, UUID userId, LocalDate workDate, WorkEntrie entrie) {
        this.id = id;
        this.userId = userId;
        this.workDate = workDate;
        this.entrie = entrie;
    }

    public static AttendanceRecord create (UUID userId, LocalDate workDate, WorkEntrie entrie) {
        return new AttendanceRecord(null, userId, workDate, entrie);
    }

    public static AttendanceRecord with(UUID id, UUID userId, LocalDate workDate, WorkEntrie entrie) {
        return new AttendanceRecord(id, userId, workDate, entrie);
    }
}
