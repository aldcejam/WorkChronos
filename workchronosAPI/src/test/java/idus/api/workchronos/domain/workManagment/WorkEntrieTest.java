package idus.api.workchronos.domain.workManagment;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WorkEntrieTest {

    @Test
    public void givenValidParams_whenCreateWorkEntrie_thenInstantiateWorkEntrie() {
        final var expectedWorkStart = LocalTime.of(9, 0);
        final var expectedWorkEnd = LocalTime.of(17, 0);
        final var expectedBreaks = List.of(WorkBreak.create(LocalTime.of(12, 0), LocalTime.of(12, 30)));

        final var actualWorkEntrie = new WorkEntrie(expectedWorkStart, expectedWorkEnd, expectedBreaks);

        Assertions.assertNotNull(actualWorkEntrie);
        Assertions.assertEquals(expectedWorkStart, actualWorkEntrie.getWorkStart());
        Assertions.assertEquals(expectedWorkEnd, actualWorkEntrie.getWorkEnd());
        Assertions.assertEquals(expectedBreaks, actualWorkEntrie.getBreaks());
    }

    @Test
    public void givenStartWork_whenWorkStarts_thenWorkStartIsSet() {
        final var expectedWorkStart = LocalTime.of(8, 0);

        final var actualWorkEntrie = WorkEntrie.startWork(expectedWorkStart);

        Assertions.assertNotNull(actualWorkEntrie);
        Assertions.assertEquals(expectedWorkStart, actualWorkEntrie.getWorkStart());
        Assertions.assertNull(actualWorkEntrie.getWorkEnd());
        Assertions.assertTrue(actualWorkEntrie.getBreaks().isEmpty());
    }

    @Test
    public void givenWorkWithoutStart_whenEndWork_thenThrowException() {
        final var workEntrie = new WorkEntrie();

        final var exception = Assertions.assertThrows(IllegalStateException.class, () -> workEntrie.finishWork(LocalTime.of(18, 0)));

        Assertions.assertEquals("Work must be started before ending it", exception.getMessage());
    }

    @Test
    public void givenWorkAlreadyEnded_whenEndWork_thenThrowException() {
        final var workEntrie = new WorkEntrie(
                LocalTime.of(9, 0),
                LocalTime.of(17, 0),
                new ArrayList<>()
        );

        final var exception = Assertions.assertThrows(IllegalStateException.class, () -> workEntrie.finishWork(LocalTime.of(18, 0)));

        Assertions.assertEquals("Work has already ended", exception.getMessage());
    }

    @Test
    public void givenWorkWithoutStart_whenStartBreak_thenThrowException() {
        final var workEntrie = new WorkEntrie();

        final var exception = Assertions.assertThrows(IllegalStateException.class, () -> workEntrie.startBreak(LocalTime.of(10, 0)));

        Assertions.assertEquals("Work must be started before adding a break", exception.getMessage());
    }

    @Test
    public void givenBreakInProgress_whenStartNewBreak_thenThrowException() {
        final var breaks = new ArrayList<WorkBreak>();
        breaks.add(WorkBreak.create(LocalTime.of(10, 0), null));
        final var workEntrie = new WorkEntrie(LocalTime.of(9, 0), null, breaks);

        final var exception = Assertions.assertThrows(IllegalStateException.class, () -> workEntrie.startBreak(LocalTime.of(11, 0)));

        Assertions.assertEquals("Cannot start a new break while there is an unfinished break", exception.getMessage());
    }

    @Test
    public void givenNoBreaks_whenEndBreak_thenThrowException() {
        final var workEntrie = new WorkEntrie(LocalTime.of(9, 0), null, new ArrayList<>());

        final var exception = Assertions.assertThrows(IllegalStateException.class, () -> workEntrie.endBreak(LocalTime.of(10, 0)));

        Assertions.assertEquals("Cannot end a break when there are no breaks to end", exception.getMessage());
    }

    @Test
    public void givenValidBreak_whenEndBreak_thenSetBreakEnd() {
        final var breakStart = LocalTime.of(10, 0);
        final var breakEnd = LocalTime.of(10, 30);
        final var breaks = new ArrayList<WorkBreak>();
        breaks.add(WorkBreak.create(breakStart, null));
        final var workEntrie = new WorkEntrie(LocalTime.of(9, 0), null, breaks);

        workEntrie.endBreak(breakEnd);

        Assertions.assertEquals(breakEnd, workEntrie.getBreaks().get(0).getEnd());
    }

    @Test
    public void givenValidJson_whenFromJson_thenReturnWorkEntrie() {
        final var json = """
                {
                    "workStart": "09:00:00",
                    "workEnd": "17:00:00",
                    "breaks": [{"start": "12:00:00", "end": "12:30:00"}]
                }
                """;

        final var actualWorkEntrie = WorkEntrie.fromString(json);

        Assertions.assertNotNull(actualWorkEntrie);
        Assertions.assertEquals(LocalTime.of(9, 0), actualWorkEntrie.getWorkStart());
        Assertions.assertEquals(LocalTime.of(17, 0), actualWorkEntrie.getWorkEnd());
        Assertions.assertEquals(1, actualWorkEntrie.getBreaks().size());
    }
}
