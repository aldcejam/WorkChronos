package idus.api.workchronos.domain.workManagment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class AttendanceRecordTest {

    @Test
    public void givenUserNotWorking_whenStartDay_thenCreateAttendanceRecord() {
        final var userId = UUID.randomUUID();

        final var attendanceRecord = AttendanceRecord.startDay(userId, null);

        Assertions.assertNotNull(attendanceRecord);
        Assertions.assertEquals(userId, attendanceRecord.getUserId());
        Assertions.assertEquals(LocalDate.now(), attendanceRecord.getWorkDate());
        Assertions.assertNotNull(attendanceRecord.getEntrie());
        Assertions.assertEquals(LocalTime.now().getHour(), attendanceRecord.getEntrie().getWorkStart().getHour());
    }

    @Test
    public void givenUserAlreadyWorking_whenStartDay_thenThrowException() {
        final var userId = UUID.randomUUID();
        final var entrie = WorkEntrie.startWork(LocalTime.now());
        final var latestRecord = AttendanceRecord.with(
                UUID.randomUUID(),
                userId,
                LocalDate.now(),
                entrie,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        final var exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                AttendanceRecord.startDay(userId, latestRecord)
        );

        Assertions.assertEquals("User is already working", exception.getMessage());
    }

    @Test
    public void givenUserWorking_whenStartBreak_thenUpdateBreakStart() {
        final var userId = UUID.randomUUID();
        final var entrie = WorkEntrie.startWork(LocalTime.of(9, 0));
        final var latestRecord = AttendanceRecord.with(
                UUID.randomUUID(),
                userId,
                LocalDate.now(),
                entrie,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        final var attendanceRecord = latestRecord.startBreak();

        Assertions.assertNotNull(attendanceRecord.getEntrie().getBreaks());
        Assertions.assertEquals(1, attendanceRecord.getEntrie().getBreaks().size());
        Assertions.assertNotNull(attendanceRecord.getEntrie().getBreaks().get(0).getStart());
        Assertions.assertEquals(LocalTime.now().getHour(), attendanceRecord.getEntrie().getBreaks().get(0).getStart().getHour());
    }

    @Test
    public void givenUserNotWorking_whenStartBreak_thenThrowException() {
        final var userId = UUID.randomUUID();
        final var entrie = WorkEntrie.startWork(LocalTime.of(9, 0));
        final var latestRecord = AttendanceRecord.with(
                UUID.randomUUID(),
                userId,
                LocalDate.now(),
                entrie,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        latestRecord.finishDay();

        final var exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                latestRecord.startBreak()
        );

        Assertions.assertEquals("User is not working", exception.getMessage());
    }

    @Test
    public void givenUserWorking_whenFinishBreak_thenUpdateBreakEnd() {
        final var userId = UUID.randomUUID();
        final var entrie = WorkEntrie.startWork(LocalTime.of(9, 0));
        final var latestRecord = AttendanceRecord.with(
                UUID.randomUUID(),
                userId,
                LocalDate.now(),
                entrie,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        latestRecord.startBreak();
        final var attendanceRecord = latestRecord.finishBreak();

        Assertions.assertNotNull(attendanceRecord.getEntrie().getBreaks().get(0).getEnd());
        Assertions.assertEquals(LocalTime.now().getHour(), attendanceRecord.getEntrie().getBreaks().get(0).getEnd().getHour());
    }

    @Test
    public void givenUserNotWorking_whenFinishBreak_thenThrowException() {
        final var userId = UUID.randomUUID();
        final var entrie = WorkEntrie.startWork(LocalTime.of(9, 0));
        final var latestRecord = AttendanceRecord.with(
                UUID.randomUUID(),
                userId,
                LocalDate.now(),
                entrie,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        latestRecord.finishDay();

        final var exception = Assertions.assertThrows(IllegalArgumentException.class, () ->
                latestRecord.finishBreak()
        );

        Assertions.assertEquals("User is not working", exception.getMessage());
    }

    @Test
    public void givenUserWorking_whenFinishDay_thenUpdateWorkEnd() {
        final var userId = UUID.randomUUID();
        final var entrie = WorkEntrie.startWork(LocalTime.of(9, 0));
        final var latestRecord = AttendanceRecord.with(
                UUID.randomUUID(),
                userId,
                LocalDate.now(),
                entrie,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        final var attendanceRecord = latestRecord.finishDay();

        Assertions.assertNotNull(attendanceRecord.getEntrie().getWorkEnd());
        Assertions.assertEquals(LocalTime.now().getHour(), attendanceRecord.getEntrie().getWorkEnd().getHour());
    }

    @Test
    public void givenValidParams_whenCreateWithWorkEntrie_thenInstantiateAttendanceRecord() {
        final var id = UUID.randomUUID();
        final var userId = UUID.randomUUID();
        final var workDate = LocalDate.of(2024, 12, 21);
        final var entrie = WorkEntrie.startWork(LocalTime.of(9, 0));
        final var createdAt = LocalDateTime.of(2024, 12, 21, 10, 0);
        final var updatedAt = LocalDateTime.of(2024, 12, 21, 12, 0);

        final var attendanceRecord = AttendanceRecord.with(id, userId, workDate, entrie, createdAt, updatedAt);

        Assertions.assertNotNull(attendanceRecord);
        Assertions.assertEquals(id, attendanceRecord.getId());
        Assertions.assertEquals(userId, attendanceRecord.getUserId());
        Assertions.assertEquals(workDate, attendanceRecord.getWorkDate());
        Assertions.assertEquals(entrie, attendanceRecord.getEntrie());
        Assertions.assertEquals(createdAt, attendanceRecord.getCreatedAt());
        Assertions.assertEquals(updatedAt, attendanceRecord.getUpdatedAt());
    }

    @Test
    public void givenValidParams_whenCreateWithJsonEntrie_thenInstantiateAttendanceRecord() {
        final var id = UUID.randomUUID();
        final var userId = UUID.randomUUID();
        final var workDate = LocalDate.of(2024, 12, 21);
        final var entrieJson = """
                {
                    "workStart": "09:00:00",
                    "workEnd": "17:00:00",
                    "breaks": [{"start": "12:00:00", "end": "12:30:00"}]
                }
                """;
        final var createdAt = LocalDateTime.of(2024, 12, 21, 10, 0);
        final var updatedAt = LocalDateTime.of(2024, 12, 21, 12, 0);

        final var attendanceRecord = AttendanceRecord.with(id, userId, workDate, entrieJson, createdAt, updatedAt);

        Assertions.assertNotNull(attendanceRecord);
        Assertions.assertEquals(id, attendanceRecord.getId());
        Assertions.assertEquals(userId, attendanceRecord.getUserId());
        Assertions.assertEquals(workDate, attendanceRecord.getWorkDate());
        Assertions.assertNotNull(attendanceRecord.getEntrie());
        Assertions.assertEquals(LocalTime.of(9, 0), attendanceRecord.getEntrie().getWorkStart());
        Assertions.assertEquals(LocalTime.of(17, 0), attendanceRecord.getEntrie().getWorkEnd());
        Assertions.assertEquals(1, attendanceRecord.getEntrie().getBreaks().size());
        Assertions.assertEquals(LocalTime.of(12, 0), attendanceRecord.getEntrie().getBreaks().get(0).getStart());
        Assertions.assertEquals(LocalTime.of(12, 30), attendanceRecord.getEntrie().getBreaks().get(0).getEnd());
        Assertions.assertEquals(createdAt, attendanceRecord.getCreatedAt());
        Assertions.assertEquals(updatedAt, attendanceRecord.getUpdatedAt());
    }
}

