package ex36ToDo;

import Nobilities.ex36ToDo.ID;
import Nobilities.ex36ToDo.ToDoApp;
import Nobilities.ex36ToDo.Viewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DeleteTest {
//### Deletion
//- Allow users to delete tasks with to delete <ID> command.


    @Test
    public void testDeleteFeature() {
        final var app = new ToDoApp();
        final var viewer = new Viewer(app);
        final var now = Instant.now();

        app.create((ID.Valid) ID.create("id"), "task 1");
        app.create((ID.Valid) ID.create("id2"), "task 2", now.plus(1, ChronoUnit.DAYS));
        app.create((ID.Valid) ID.create("id3"), "task 3", now.plus(3, ChronoUnit.DAYS));

        final var before = viewer.forToday(now);
        app.delete((ID.Valid) ID.create("id"));
        app.delete((ID.Valid) ID.create("id2"));
        final var after = viewer.forToday(now);

        Assertions.assertEquals(before.size() - 2, after.size());
    }
}