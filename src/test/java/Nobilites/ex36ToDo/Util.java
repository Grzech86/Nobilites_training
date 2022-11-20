package Nobilites.ex36ToDo;

import Nobilities.ex36ToDo.Task;

import java.util.Collection;
import java.util.LinkedList;
import java.util.function.Function;

public class Util {
    static public <T> Collection<T> map(Collection<Task> tasks, Function<Task, T> mapper) {
        final var mapped = new LinkedList<T>();
        for (final var task : tasks) {
            mapped.add(mapper.apply(task));
        }
        return mapped;
    }
}