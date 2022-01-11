package ua.edu.sumdu.j2se.kushnir.tasks.model;

import java.io.Serializable;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Serializable {

    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index);

    abstract ListTypes.types getListType();

    public abstract Stream<Task> getStream();
}
