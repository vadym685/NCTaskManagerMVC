package ua.edu.sumdu.j2se.kushnir.tasks;

import java.util.stream.Stream;

abstract class AbstractTaskList implements Iterable<Task> {

    abstract void add(Task task);

    abstract boolean remove(Task task);

    abstract int size();

    abstract Task getTask(int index);

    abstract ListTypes.types getListType();

    /*
        Method that returns a subset of tasks that are scheduled to run at least once in an interval
    */
    public final AbstractTaskList incoming(int from, int to) throws IllegalArgumentException {
        if ((from < 0) || (to < 0)) {
            throw new IllegalArgumentException("Uncorrected argument 'from' or 'to'");
        }

        AbstractTaskList taskList = TaskListFactory.createTaskList(getListType());

//        for (int i = 0; i < size(); i++) {
//            if ((getTask(i) != null) && (getTask(i).nextTimeAfter(from) < to) && (getTask(i).nextTimeAfter(from) != -1)) {
//                taskList.add(getTask(i));
//            }
//        }
        this.getStream()
                .filter(task -> task != null && task.nextTimeAfter(from) < to && task.nextTimeAfter(from) != -1)
                .forEach(taskList::add);

        return taskList;
    }

    public abstract Stream<Task> getStream();
}
