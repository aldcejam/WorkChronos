package idus.api.workchronos.domain.workManagment;

import idus.api.workchronos.domain.ValueObject;

import java.time.Duration;
import java.time.ZonedDateTime;

public class WorkBreak extends ValueObject {
    private final ZonedDateTime start;
    private final ZonedDateTime end;

    public WorkBreak(ZonedDateTime start, ZonedDateTime end) {
        validate(start, end);
        this.start = start;
        this.end = end;
    }

    private void validate(ZonedDateTime start, ZonedDateTime end) {
        if (start.isAfter(end)) throw new IllegalArgumentException("Start time must be before end time");
    }

    public Duration getDuration() {
        return Duration.between(start, end);
    }
}
