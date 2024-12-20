package idus.api.workchronos.infra.persistence.attendanceRecord;

import com.fasterxml.jackson.annotation.JsonIgnore;
import idus.api.workchronos.infra.persistence.user.UserDB;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    @Column(name = "entries", nullable = false)
    private String entries;
}