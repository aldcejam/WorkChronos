package idus.api.workchronos.domain.workManagment.workArrangements;

import java.util.List;
import java.util.UUID;

public class WorkArrangements {
    private final UUID id;
    private final UUID userID;
    private final Byte weekelyHours;
    private final Byte dailyHours;
    private WorkScheduleType type;
    private final List<WorkSchedule> workSchedule;

    public WorkArrangements(UUID id, UUID userID, Byte weekelyHours, Byte dailyHours, WorkScheduleType type, List<WorkSchedule> workSchedule, boolean isActivated) {
        this.id = id == null ? UUID.randomUUID() : id;
        this.userID = userID;
        this.weekelyHours = weekelyHours;
        this.dailyHours = dailyHours;
        this.type = type;
        this.workSchedule = workSchedule;
    }

    public UUID id() {
        return id;
    }

    public UUID userID() {
        return userID;
    }

    public Byte weekelyHours() {
        return weekelyHours;
    }

    public Byte dailyHours() {
        return dailyHours;
    }

    public WorkScheduleType type() {
        return type;
    }

    public List<WorkSchedule> workSchedule() {
        return workSchedule;
    }
}
