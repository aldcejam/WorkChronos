package idus.api.workchronos.domain.workManagment;

import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class AttendanceRecords {
    private final UUID id;
    private final UUID userId;
    private final Date workDate;
    private final WorkEntries entries;

    public AttendanceRecords(UUID id, UUID userId, Date workDate, WorkEntries entries) {
        this.id = id;
        this.userId = userId;
        this.workDate = workDate;
        this.entries = entries;
    }

}
