package Nobilites.ex36ToDo;

import Nobilities.ex36ToDo.ID;
import Nobilities.ex36ToDo.ToDoApp;
import Nobilities.ex36ToDo.Viewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TodayTest {
//    ### Deadlines
//- Give each task an optional deadline with the deadline <ID> <date> command.
//- Show all tasks due today with the today command.

    @Test
    public void testTodayFeature() {
        final var app = new ToDoApp();
        final var viewer = new Viewer(app);
        final var now = Instant.now();

        app.create((ID.Valid) ID.create("id"), "task 1");
        app.create((ID.Valid) ID.create("id2"), "task 2", now.plus(1, ChronoUnit.DAYS));
        app.create((ID.Valid) ID.create("id3"), "task 3", now.minus(3, ChronoUnit.DAYS));
        final var ids = Util.map(viewer.forToday(now), task -> task.id());

        final var expected = List.of(ID.create("id"), ID.create("id2"));
        Assertions.assertTrue(ids.containsAll(expected));
    }
}