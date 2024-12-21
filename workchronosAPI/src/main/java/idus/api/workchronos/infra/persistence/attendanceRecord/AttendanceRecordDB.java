package idus.api.workchronos.infra.persistence.attendanceRecord;

import com.fasterxml.jackson.annotation.JsonIgnore;
import idus.api.workchronos.domain.workManagment.AttendanceRecord;
import idus.api.workchronos.infra.persistence.user.UserDB;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "attendance_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class AttendanceRecordDB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserDB user;

    @Column(name = "work_date", nullable = false)
    private LocalDate workDate;

    @Column(name = "entries", nullable = false, columnDefinition = "json")
    private String entries;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public AttendanceRecord toDomain() {
        return AttendanceRecord.with(id, user.getId(), workDate, entries, createdAt, updatedAt);
    }
}