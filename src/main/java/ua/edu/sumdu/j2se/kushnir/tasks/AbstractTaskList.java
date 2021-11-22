package ua.edu.sumdu.j2se.kushnir.tasks;

abstract class AbstractTaskList {

    abstract void add(Task task);

    abstract boolean remove(Task task);

    abstract int size();

    abstract Task getTask(int index);

    abstract ListTypes.types getListType();

    /*
        Method that returns a subset of tasks that are scheduled to run at least once in an interval
    */
    public AbstractTaskList incoming(int from, int to) throws IllegalArgumentException {
        if ((from < 0) || (to < 0)) {
            throw new IllegalArgumentException("Uncorrected argument 'from' or 'to'");
        }

        AbstractTaskList taskList = TaskListFactory.createTaskList(getListType());

        for (int i = 0; i < size(); i++) {
            if ((getTask(i) != null) && (getTask(i).nextTimeAfter(from) < to) && (getTask(i).nextTimeAfter(from) != -1)) {
                taskList.add(getTask(i));
            }
        }
        return taskList;
    }
}
