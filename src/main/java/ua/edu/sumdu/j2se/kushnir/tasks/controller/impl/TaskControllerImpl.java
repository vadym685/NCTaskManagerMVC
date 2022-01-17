package ua.edu.sumdu.j2se.kushnir.tasks.controller.impl;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import ua.edu.sumdu.j2se.kushnir.tasks.controller.controllers.TaskController;
import ua.edu.sumdu.j2se.kushnir.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kushnir.tasks.model.Task;
import ua.edu.sumdu.j2se.kushnir.tasks.view.util.Output;
import ua.edu.sumdu.j2se.kushnir.tasks.view.views.MainView;

import java.time.LocalDateTime;
import java.util.Scanner;

public class TaskControllerImpl implements TaskController {
    private static final Logger log = Logger.getLogger(TaskControllerImpl.class);
    private final MainView mainView;
    private final AbstractTaskList list;
    private final Scanner scanner;

    public TaskControllerImpl(AbstractTaskList list, MainView mainView) {
        this.list = list;
        this.mainView = mainView;
        scanner = new Scanner(System.in);
    }

    @Override
    public void createTask() {
        log.info("start create new task");
        String title = mainView.getTitle();
        if (mainView.getRepeatStatus()) {
            createRepeatedTask(title);
        } else {
            createNotRepeatedTask(title);
        }
    }

    @Override
    public void editTask() {
        log.info("start edit task");
        int index = mainView.getIndex(list) - 1;
        if (index > -1) {
            Task task = list.getTask(index);
            while (true) {
                mainView.editMenu();

                int choice = scanner.nextInt();
                if (choice == 0) {
                    System.out.println(Output.MAIN_MENU);
                    break;
                } else if (choice == 1) {
                    editTaskTitle(task);
                    break;
                } else if (choice == 2) {
                    editTaskRepeatedStatus(task);
                    break;
                } else if (choice == 3) {
                    changeTaskActivityStatus(task);
                    break;
                } else if (choice == 4) {
                    changeTaskTimeOptions(task);
                    break;
                }
            }
        }
    }

    @Override
    public void deleteTask() {
        log.info("start delete task");
        int index = mainView.getIndex(list) - 1;
        if (index > -1) {
            Task task = list.getTask(index);
            list.remove(task);
            System.out.println("Task №" + (index + 1) + " was deleted");
        }
    }

    @Override
    public void showTaskList() {
        log.info("Execute show task list action");
        mainView.showTaskList(list);
    }

    @Override
    public void showCalendar() {
        log.info("Execute show task calendar action");
        mainView.showCalendar(list);
    }

    private void createRepeatedTask(String title) {
        log.info("Creating repeated task");
        Task task;
        LocalDateTime start = mainView.getStartTime();
        LocalDateTime end = mainView.getEndTime();
        int interval = mainView.getInterval();

        if (checkParams(start, end, interval)) {
            task = new Task(title, start, end, interval);
            task.setActive(true);

            list.add(task);
            System.out.println(Output.SUCCESSFUL_ADD);
        } else {
            System.out.println(Output.FAILURE_ADD);
        }
    }

    private void createNotRepeatedTask(String title) {
        log.info("Creating not repeated task");
        Task task;
        LocalDateTime time = mainView.getTime();

        task = new Task(title, time);
        task.setActive(true);

        list.add(task);
        System.out.println(Output.SUCCESSFUL_ADD);
    }

    private boolean checkParams(@NotNull LocalDateTime start, LocalDateTime end, int interval) {
        return (start.isBefore(end) || !start.equals(end))
                && start.plusSeconds(interval).isBefore(end);
    }

    private void changeTaskTimeOptions(@NotNull Task task) {
        log.info("Changing task time options");
        if (task.isRepeated()) {
            editRepeatedTime(task);
        } else {
            editNotRepeatedTime(task);
        }
    }

    private void editRepeatedTime(Task task) {
        log.info("Changing repeated task time");
        System.out.println("-_Enter new repeated task time_-");
        while (true) {
            LocalDateTime start = mainView.getStartTime();
            LocalDateTime end = mainView.getEndTime();
            int interval = mainView.getInterval();

            if (checkParams(start, end, interval)) {
                task.setTime(start, end, interval);
                System.out.println("Repeated task has successfully changed its time");
                break;
            } else {
                System.out.println("Changing the time of the repeated task was a failure");
            }
        }
    }

    private void editNotRepeatedTime(@NotNull Task task) {
        log.info("Changing not repeated time");
        System.out.println("Enter new time to task");
        LocalDateTime time = mainView.getTime();
        task.setTime(time);
        System.out.println("Task has successfully changed");
    }

    private void editTaskTitle(@NotNull Task task) {
        log.info("Changing title to task");
        System.out.println("-_Enter new title to task: ");
        String title = mainView.getTitle();
        task.setTitle(title);
        System.out.println("Task has successfully changed");
    }

    private void editTaskRepeatedStatus(@NotNull Task task) {
        log.info("Changing task repeated status");
        System.out.println("The task has the following repetition status -> " + task.isRepeated());
        if (task.isRepeated()) {
            makeNotRepeated(task);
        } else {
            makeRepeated(task);
        }
    }

    private void makeNotRepeated(@NotNull Task task) {
        log.info("Changing: repeated task -> not repeated task");
        task.setTime(mainView.getTime());
        task.setRepeated(false);
    }

    private void makeRepeated(Task task) {
        log.info("Changing: not repeated task -> repeated task");
        LocalDateTime start = mainView.getStartTime();
        LocalDateTime end = mainView.getEndTime();
        int interval = mainView.getInterval();

        if (checkParams(start, end, interval)) {
            task.setTime(start, end, interval);
            task.setRepeated(true);
            System.out.println(Output.SUCCESSFUL_EDIT);
        } else {
            System.out.println(Output.FAILURE_EDIT);
        }
    }

    private void changeTaskActivityStatus(@NotNull Task task) {
        log.info("Changing task activity");
        System.out.print("-_Enter new task activity status: ");
        boolean activity = mainView.getActivity();
        task.setActive(activity);
        System.out.println("Task has successfully changed");
    }

}
