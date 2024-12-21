package idus.api.workchronos.application;

import idus.api.workchronos.domain.workManagment.AttendanceRecord;
import idus.api.workchronos.infra.persistence.attendanceRecord.AttendanceRecordDB;
import idus.api.workchronos.infra.persistence.attendanceRecord.AttendanceRecordRepository;
import idus.api.workchronos.infra.persistence.user.UserDB;
import idus.api.workchronos.infra.persistence.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AttendenceRecordService {

    AttendanceRecordRepository attendanceRecordRepository;
    UserRepository userRepository;

    public AttendenceRecordService(AttendanceRecordRepository attendanceRecordRepository, UserRepository userRepository) {
        this.attendanceRecordRepository = attendanceRecordRepository;
        this.userRepository = userRepository;
    }

    public AttendanceRecord startDay(UUID userID) {
        UserDB userDB = userRepository.findById(userID).orElseThrow(() -> new RuntimeException("User not found"));
        AttendanceRecordDB latestRecordDB = attendanceRecordRepository.findLatestByUser((userDB)).orElse(null);
        AttendanceRecord latestRecord = latestRecordDB != null ? latestRecordDB.toDomain() : null;

        AttendanceRecord record = AttendanceRecord.startDay(userID, latestRecord);

        AttendanceRecordDB recordDB = AttendanceRecordDB.builder()
                .workDate(record.getWorkDate())
                .user(userDB)
                .entries(record.getEntrie().toString())
                .build();

        attendanceRecordRepository.save(recordDB);

        return record;
    }


}
