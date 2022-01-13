package ua.edu.sumdu.j2se.kushnir.tasks.view.util;

public interface Output {

    String CALENDAR_HEAD = "#________\t\tCALENDAR\t\t________#";
    String CALENDAR_BOTTOM = "#\t________\t\tEND\t\t________\t#";
    String HEAD_REPEATED_TASK = "#\tTitle\t\t\tStart\t\t\t\tEnd\t\t\t\tInterval\t\tActive";
    String HEAD_NOT_REPEATED_TASK = "#\tTitle\t\t\tTime\t\t\tActive";
    String SUCCESSFUL_ADD = "Addition the task was successful";
    String FAILURE_ADD = "Addition the task was failure";
    String SUCCESSFUL_EDIT = "Task editing was successful";
    String FAILURE_EDIT = "Task editing was failure";
    String EMPTY_LIST = "The task list is empty. " +
            "You can fill it by adding at least one task.";
    String MAIN_MENU = "Returning to main menu";


    static void printMainMenu(){
        System.out.println("            Task Manager                ");
        System.out.println("# 1) Add task");
        System.out.println("# 2) Edit task");
        System.out.println("# 3) Delete task");
        System.out.println("# 4) Show tasks' list");
        System.out.println("# 5) Show tasks' calendar");
        System.out.println("# 0) Exit program");
        System.out.println("________________________________________");
        System.out.println("@ Your choice: ");
    }

    static void printEditMenu(){
        System.out.println("                Edit Menu               ");
        System.out.println("# 1) Task title");
        System.out.println("# 2) Task repeated status");
        System.out.println("# 3) Task active");
        System.out.println("# 4) Task time");
        System.out.println("# 0) To main menu");
        System.out.println("________________________________________");
        System.out.println("@ Your choice: ");
    }

    static void printlnCalendarDate(String date) {
        System.out.println("@\t______\t" + date + "\t______\t@");
    }

    static void printlnCalendarTask(String title) {
        System.out.println("\t\t@\t~~~\t" + title + "\t~~~\t@");
    }

    static void notifyCalendarDate(String date) {
        System.out.println("%\t!!!!!!\t" + date + "\t!!!!!!\t%");
    }

    static void notifyCalendarTask(String title) {
        System.out.println("\t\t%\t&&&\t" + title + "\t&&&\t%");
    }
}
