package Nobilites.ex36ToDo;

import Nobilities.ex36ToDo.ID;
import Nobilities.ex36ToDo.ToDoApp;
import Nobilities.ex36ToDo.Viewer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class DeadlineTest {
//    ### Views
//- View tasks by date with the view by date command.
//            - View tasks by deadline with the view by deadline command.
//            - Don’t remove the functionality that allows users to view tasks by project, but change the command to view by project.

    @Test
    public void testViewByDeadline() {
        final var app = new ToDoApp();
        final var viewer = new Viewer(app);
        final var now = Instant.now();

        app.create((ID.Valid) ID.create("id"), "task 1");
        app.create((ID.Valid) ID.create("id2"), "task 2", now.plus(1, ChronoUnit.DAYS));
        app.create((ID.Valid) ID.create("id3"), "task 3", now.plus(3, ChronoUnit.DAYS));

        final var byDeadline = Util.map(viewer.byDeadline(), task -> task.id());

        Assertions.assertEquals(List.of(ID.create("id2"), ID.create("id3"), ID.create("id")), byDeadline);
    }
}