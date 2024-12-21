package idus.api.workchronos.domain.workManagment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import idus.api.workchronos.domain.ValueObject;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class WorkEntrie extends ValueObject {
    private LocalTime workStart;
    private LocalTime workEnd;
    private List<WorkBreak> breaks;

    @JsonCreator
    public WorkEntrie(@JsonProperty("workStart") LocalTime workStart,
                       @JsonProperty("workEnd") LocalTime workEnd,
                       @JsonProperty("breaks") List<WorkBreak> breaks) {
        this.workStart = workStart;
        this.workEnd = workEnd;
        this.breaks = breaks != null ? breaks : new ArrayList<>();
    }

    public static WorkEntrie startWork(LocalTime workStart) {
        return new WorkEntrie(workStart, null, new ArrayList<>());
    }

    @JsonIgnore
    public WorkBreak getCurrentBreak() {
        if (this.breaks.isEmpty()) return null;
        WorkBreak lastBreak = this.breaks.get(this.breaks.size() - 1);
        if (lastBreak.getEnd() != null) return null;
        return lastBreak;
    }

    @JsonIgnore
    public boolean isWorking() {
        return this.workStart != null && this.workEnd == null;
    }

    public void finishWork(LocalTime workEnd) {
        if (this.workStart == null) throw new IllegalStateException("Work must be started before ending it");
        if (this.workEnd != null) throw new IllegalStateException("Work has already ended");
        if (!this.breaks.isEmpty() && this.getCurrentBreak().getEnd() == null) this.getCurrentBreak().finishBreak(workEnd);
        this.workEnd = workEnd;
    }

    public void startBreak(LocalTime breakStart) {
        if (this.workStart == null) throw new IllegalStateException("Work must be started before adding a break");
        if (this.workEnd != null) throw new IllegalStateException("Work has already ended");
        for (WorkBreak workBreak : this.breaks) {
            if (workBreak.getEnd() == null) throw new IllegalStateException("Cannot start a new break while there is an unfinished break");
        }
        this.breaks.add(WorkBreak.create(breakStart, null));
    }

    public void finishBreak(LocalTime breakEnd) {
        if (this.workStart == null) throw new IllegalStateException("Work must be started before adding a break");
        if (this.workEnd != null) throw new IllegalStateException("Work has already ended");
        if (this.breaks.isEmpty()) throw new IllegalStateException("Cannot end a break when there are no breaks to end");
        WorkBreak lastBreak = this.breaks.get(this.breaks.size() - 1);
        lastBreak.finishBreak(breakEnd);
    }


    public String toString() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule()); // Registrar o módulo JSR310
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static WorkEntrie fromString(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.readValue(json, WorkEntrie.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}