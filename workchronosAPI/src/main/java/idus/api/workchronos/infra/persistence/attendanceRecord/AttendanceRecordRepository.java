package idus.api.workchronos.infra.persistence.attendanceRecord;

import idus.api.workchronos.infra.persistence.user.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecordDB, UUID> {
    @Query("SELECT ar FROM AttendanceRecordDB ar WHERE ar.user = :user ORDER BY ar.createdAt DESC")
    Optional<AttendanceRecordDB> findLatestByUser(UserDB user);
}
