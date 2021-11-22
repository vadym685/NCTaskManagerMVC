package ua.edu.sumdu.j2se.kushnir.tasks;

public class TaskListFactory {

    /*
     Method that creates an ArrayTaskList and LinkedTaskList object depending on the parameter it contains
     is transmitted
    */
    public static AbstractTaskList createTaskList(ListTypes.types type) {

        if (type != null) {
            if (type == ListTypes.types.ARRAY) {
                return new ArrayTaskList();
            } else if (type == ListTypes.types.LINKED) {
                return new LinkedTaskList();
            } else {
                throw new IllegalArgumentException("Do not found listType " + type + " on ListTypesEnum");
            }
        } else {
            return null;
        }
    }
}
