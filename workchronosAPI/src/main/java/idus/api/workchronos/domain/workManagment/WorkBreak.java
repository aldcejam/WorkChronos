package idus.api.workchronos.domain.workManagment;

import idus.api.workchronos.domain.ValueObject;
import lombok.Getter;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Date;

@Getter
public class WorkBreak extends ValueObject {
    private final Date start;
    private final Date end;
    private final String description;

    public WorkBreak(Date start, Date end, String description) {
        this.start = start;
        this.end = end;
        this.description = description;
    }

    public static WorkBreak create(Date start, Date end) {
        return new WorkBreak(start, end, null);
    }

    public static WorkBreak create(Date start, Date end, String description) {
        return new WorkBreak(start, end, description);
    }
}
