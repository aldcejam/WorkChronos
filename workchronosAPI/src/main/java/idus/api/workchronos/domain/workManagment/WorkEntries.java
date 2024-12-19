package idus.api.workchronos.domain.workManagment;

import idus.api.workchronos.domain.ValueObject;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class WorkEntries extends ValueObject {
    private final LocalDateTime workStart;
    private final LocalDateTime workEnd;
    private final List<WorkBreak> breaks;

    public WorkEntries(LocalDateTime workStart, LocalDateTime workEnd, List<WorkBreak> breaks) {
        this.workStart = workStart;
        this.workEnd = workEnd;
        this.breaks = breaks;
    }

}
