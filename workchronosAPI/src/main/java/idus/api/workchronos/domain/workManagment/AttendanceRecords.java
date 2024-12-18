package idus.api.workchronos.domain.workManagment;

import java.time.LocalDate;
import java.util.UUID;

public class AttendanceRecords {
    private final UUID id;
    private final UUID userId;
    private final UUID workArrangementId;
    private final LocalDate workDate;
    private final WorkEntries entries;

    public AttendanceRecords(UUID id, UUID userId, UUID workArrangementId, LocalDate workDate, WorkEntries entries) {
        this.id = id;
        this.userId = userId;
        this.workArrangementId = workArrangementId;
        this.workDate = workDate;
        this.entries = entries;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getWorkArrangementId() {
        return workArrangementId;
    }

    public LocalDate getWorkDate() {
        return workDate;
    }

    public WorkEntries getEntries() {
        return entries;
    }
}
