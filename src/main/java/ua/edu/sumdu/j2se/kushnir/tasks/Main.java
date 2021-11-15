package ua.edu.sumdu.j2se.kushnir.tasks;

public class Main {

    public static void main(String[] args) {
//		System.out.println("Hello");
        Task task = new Task("Shopping1", 11, 14, 2);
        Task task1 = new Task("Shopping2", 11, 14, 2);
        Task task2 = new Task("Shopping3", 11, 14, 2);
        task.setActive(true);

        int nextTimeAfter = task.nextTimeAfter(11);
        System.out.println(nextTimeAfter);

        LinkedTaskList linkedTaskList = new LinkedTaskList();

        linkedTaskList.add(task);
        linkedTaskList.add(task1);
        linkedTaskList.add(task2);
        linkedTaskList.remove(task1);

        Task head = linkedTaskList.getTask(0);
        Task tempTask1 = linkedTaskList.getTask(1);
		Task tempTask2 = linkedTaskList.getTask(2);
    }
}
