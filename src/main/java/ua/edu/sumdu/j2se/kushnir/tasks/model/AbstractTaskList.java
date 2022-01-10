package ua.edu.sumdu.j2se.kushnir.tasks.model;

import java.io.Serializable;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Serializable {

    abstract void add(Task task);

    abstract boolean remove(Task task);

    abstract int size();

    abstract Task getTask(int index);

    abstract ListTypes.types getListType();

    public abstract Stream<Task> getStream();
}
