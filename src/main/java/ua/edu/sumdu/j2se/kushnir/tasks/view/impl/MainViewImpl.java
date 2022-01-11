package ua.edu.sumdu.j2se.kushnir.tasks.view.impl;

import ua.edu.sumdu.j2se.kushnir.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kushnir.tasks.view.util.Output;
import ua.edu.sumdu.j2se.kushnir.tasks.view.views.MainView;
import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.kushnir.tasks.view.views.TaskViev;

import java.time.LocalDateTime;
import java.util.Scanner;

public class MainViewImpl implements MainView {
    private static final Logger log = Logger.getLogger(MainView.class);
    private final TaskViev taskView;
    private final Scanner scanner;

    public MainViewImpl() {
        scanner = new Scanner(System.in);
        taskView = new TaskViewImpl(scanner);
    }
    /**
     * Displays application main menu.
     */
    @Override
    public void showMenu() {
        log.info("Displaying the main user menu");
        Output.println("==============Task Manager==============");
        Output.println("# 1) Add task");
        Output.println("# 2) Edit task");
        Output.println("# 3) Delete task");
        Output.println("# 4) Show tasks' list");
        Output.println("# 5) Show tasks' calendar");
        Output.println("# 0) Exit program");
        Output.println("========================================");
        Output.print("@ Your choice: ");
    }

    /**
     * Displays application edit menu.
     */
    @Override
    public void editMenu() {
        log.info("Displaying the user editing menu");
        Output.println("==============Edit Menu==============");
        Output.println("# 1) Task title");
        Output.println("# 2) Task repeated status");
        Output.println("# 3) Task active");
        Output.println("# 4) Task time");
        Output.println("# 0) To main menu");
        Output.println("========================================");
        Output.print("@ Your choice: ");
    }

    /**
     * Displays task list menu.
     *
     * @param list - general task list
     */
    @Override
    public void showTaskList(AbstractTaskList list) {
        log.info("Showing tasks' list");
        taskView.showList(list);
    }

    /**
     * Displays task calendar menu.
     *
     * @param list - general task list
     */
    @Override
    public void showCalendar(AbstractTaskList list) {
        log.info("Showing tasks' calendar");
        taskView.getCalendar(list);
    }

    /**
     * Displays user choice menu.
     *
     * @return true if user input equal to yes, false if equal to no
     */
    @Override
    public boolean checkUserChoice() {
        log.info("Checking user answer");
        return userAnswer().equalsIgnoreCase("yes");
    }

    private String userAnswer() {
        log.info("Parsing the user response");
        String answer = scanner.nextLine();
        while (!answer.equalsIgnoreCase("yes")
                && !answer.equalsIgnoreCase("no")) {
            Output.print("You should select #Yes# or #No#: ");
            answer = scanner.nextLine();
        }
        return answer;
    }

    /**
     * Displays task title input menu.
     *
     * @return task title
     */
    @Override
    public String getTitle() {
        log.info("Getting task title");
        return taskView.getTitle();
    }

    /**
     * Displays task repeat status input menu.
     *
     * @return true if task is repeated, false if task is not repeated
     */
    @Override
    public boolean getRepeatStatus() {
        log.info("Getting repeated status");
        return taskView.getRepeatStatus();
    }

    /**
     * Displays task start time input menu.
     *
     * @return start time
     */
    @Override
    public LocalDateTime getStartTime() {
        log.info("Getting start time");
        return taskView.getStartTime();
    }

    /**
     * Displays task end time input menu.
     *
     * @return end time
     */
    @Override
    public LocalDateTime getEndTime() {
        log.info("Getting end time");
        return taskView.getEndTime();
    }

    /**
     * Display task interval input menu.
     *
     * @return interval
     */
    @Override
    public int getInterval() {
        log.info("Getting task interval");
        return taskView.getInterval();
    }

    /**
     * Displays task time input menu.
     *
     * @return time
     */
    @Override
    public LocalDateTime getTime() {
        log.info("Getting time");
        return taskView.getTime();
    }

    /**
     * Displays task index for action input menu.
     *
     * @param list - general task list
     * @return task index
     */
    @Override
    public int getIndex(AbstractTaskList list) {
        log.info("Getting task index for action");
        return taskView.getTaskIndex(list);
    }

    /**
     * Displays task activity input menu.
     *
     * @return true if task is active, false if task is inactive
     */
    @Override
    public boolean getActivity() {
        log.info("Getting task activity");
        return taskView.getActivity();
    }
}
