package ua.edu.sumdu.j2se.kushnir.tasks;

public class Main {

	public static void main(String[] args) {
//		System.out.println("Hello");
		Task task = new Task("Shopping",11,14,2);
		Task task1 = new Task("Shopping",11,14,2);
		Task task2 = new Task("Shopping",11,14,2);
		task.setActive(true);

		int nextTimeAfter = task.nextTimeAfter(11);
		System.out.println(nextTimeAfter);

		ArrayTaskList arrayTaskList = new ArrayTaskList();

		arrayTaskList.add(task);
		arrayTaskList.add(task1);
		arrayTaskList.add(task2);
		arrayTaskList.add(null);

	}
}
