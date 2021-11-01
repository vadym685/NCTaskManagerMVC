package ua.edu.sumdu.j2se.kushnir.tasks;

public class Main {

	public static void main(String[] args) {
//		System.out.println("Hello");
		Task task = new Task("Shopping",11,14,2);
		task.setActive(true);

		int nextTimeAfter = task.nextTimeAfter(11);
		System.out.println(nextTimeAfter);

	}
}
