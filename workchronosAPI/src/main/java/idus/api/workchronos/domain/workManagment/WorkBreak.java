package idus.api.workchronos.domain.workManagment;

import idus.api.workchronos.domain.ValueObject;
import lombok.Getter;

import java.time.Duration;
import java.time.ZonedDateTime;

@Getter
public class WorkBreak extends ValueObject {
    private final ZonedDateTime start;
    private final ZonedDateTime end;

    public WorkBreak(ZonedDateTime start, ZonedDateTime end) {
        this.start = start;
        this.end = end;
    }
}
