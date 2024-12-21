package idus.api.workchronos.domain.workManagment;
import idus.api.workchronos.domain.ValueObject;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class WorkBreak extends ValueObject {
    private LocalTime start;
    private LocalTime end;
    private String description;

    private WorkBreak(LocalTime start, LocalTime end, String description) {
        this.start = start;
        this.end = end;
        this.description = description;
    }

    public static WorkBreak create(LocalTime start, LocalTime end) {
        return new WorkBreak(start, end, null);
    }

    public static WorkBreak create(LocalTime start, LocalTime end, String description) {
        return new WorkBreak(start, end, description);
    }

    public void finishBreak(LocalTime end) {
        if (this.start == null) throw new IllegalStateException("Break must be started before finishing it");
        if (this.end != null) throw new IllegalStateException("Break has already ended");
        this.end = end;
    }
}