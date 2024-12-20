package idus.api.workchronos.domain.workManagment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import idus.api.workchronos.domain.ValueObject;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
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
        this.breaks = breaks;
    }

    public static WorkEntrie create(LocalTime workStart, LocalTime workEnd, List<WorkBreak> breaks) {
        return new WorkEntrie(workStart, workEnd, breaks);
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

    public static WorkEntrie fromJson(String json) {
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