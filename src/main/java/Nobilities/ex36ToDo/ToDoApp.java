package Nobilities.ex36ToDo;

import java.time.Instant;
import java.util.Collection;
import java.util.LinkedList;

public class ToDoApp {
    private final Collection<Task> tasks = new LinkedList<>();

    public Collection<Task> tasks() {
        return tasks;
    }

    public void delete(ID.Valid id) {
        tasks.removeIf(task -> task.id().equals(id));
    }

    public void create(ID.Valid id, String description) {
        add(new Task(id, description, Instant.MAX));
    }

    public void create(ID.Valid id, String description, Instant when) {
        add(new Task(id, description, when));
    }

    private void add(Task task) {
        tasks.add(task);
    }
}