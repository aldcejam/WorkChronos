package idus.api.workchronos.domain.workManagment.workArrangements;
import idus.api.workchronos.domain.ValueObject;
import idus.api.workchronos.domain.workManagment.WorkBreak;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class WorkSchedule extends ValueObject {
    private final LocalTime start;
    private final LocalTime end;
    private final List<WorkBreak> breaks;
    private DayOfWeek dayOfWeek;

    private WorkSchedule(LocalTime start, LocalTime end, List<WorkBreak> breaks, DayOfWeek dayOfWeek) {
        this.validate(start, end, breaks);
        this.start = start;
        this.end = end;
        this.breaks = breaks;
        this.dayOfWeek = dayOfWeek;
    }

    public static WorkSchedule create(LocalTime start, LocalTime end, List<WorkBreak> breaks, DayOfWeek dayOfWeek) {
        return new WorkSchedule(start, end, breaks, dayOfWeek);
    }

    public LocalTime start() {
        return start;
    }

    public LocalTime end() {
        return end;
    }

    public List<WorkBreak> breaks() {
        return breaks;
    }

    public DayOfWeek dayOfWeek() {
        return dayOfWeek;
    }

    private void validate(LocalTime start, LocalTime end, List<WorkBreak> breaks) {
        if (start.isAfter(end)) throw new IllegalArgumentException("Start time must be before end time");
    }
}
