package idus.api.workchronos.infra.persistence.attendanceRecord;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecordDB, UUID> {
}
