package Nobilities.ex36ToDo;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Viewer {
    private final ToDoApp toDoApp;

    public Viewer(ToDoApp toDoApp) {
        this.toDoApp = toDoApp;
    }

    public Collection<Task> forToday(Instant instant) {
        final var today = instant.truncatedTo(ChronoUnit.DAYS);

        final var filtered = new LinkedList<Task>();

        for (final var task : toDoApp.tasks()) {
            if (today.isAfter(task.deadline())) {
                continue;
            }
            filtered.add(task);
        }
        return filtered;
    }

    public Collection<Task> byDeadline() {
        final var sorted = new LinkedList<>(toDoApp.tasks());
        sorted.sort(Comparator.comparing(Task::deadline));
        return sorted;
    }
}