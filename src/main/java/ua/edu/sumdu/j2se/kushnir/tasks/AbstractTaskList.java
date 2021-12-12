package ua.edu.sumdu.j2se.kushnir.tasks;

import java.util.stream.Stream;

abstract class AbstractTaskList implements Iterable<Task> {

    abstract void add(Task task);

    abstract boolean remove(Task task);

    abstract int size();

    abstract Task getTask(int index);

    abstract ListTypes.types getListType();

    public abstract Stream<Task> getStream();
}
