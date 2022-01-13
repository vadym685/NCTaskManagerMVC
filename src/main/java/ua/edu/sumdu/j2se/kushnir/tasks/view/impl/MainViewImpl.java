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

    @Override
    public void showMenu() {
        log.info("Displaying the main user menu");
        Output.printMainMenu();
    }

    @Override
    public void editMenu() {
        log.info("Displaying the user editing menu");
        Output.printEditMenu();
    }

    @Override
    public void showTaskList(AbstractTaskList list) {
        log.info("Showing tasks' list");
        taskView.showList(list);
    }

    @Override
    public void showCalendar(AbstractTaskList list) {
        log.info("Showing tasks' calendar");
        taskView.getCalendar(list);
    }

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
            System.out.print("You should select #Yes# or #No#: ");
            answer = scanner.nextLine();
        }
        return answer;
    }

    @Override
    public String getTitle() {
        log.info("Getting task title");
        return taskView.getTitle();
    }

    @Override
    public boolean getRepeatStatus() {
        log.info("Getting repeated status");
        return taskView.getRepeatStatus();
    }

    @Override
    public LocalDateTime getStartTime() {
        log.info("Getting start time");
        return taskView.getStartTime();
    }

    @Override
    public LocalDateTime getEndTime() {
        log.info("Getting end time");
        return taskView.getEndTime();
    }

    @Override
    public int getInterval() {
        log.info("Getting task interval");
        return taskView.getInterval();
    }

    @Override
    public LocalDateTime getTime() {
        log.info("Getting time");
        return taskView.getTime();
    }

    @Override
    public int getIndex(AbstractTaskList list) {
        log.info("Getting task index for action");
        return taskView.getTaskIndex(list);
    }

    @Override
    public boolean getActivity() {
        log.info("Getting task activity");
        return taskView.getActivity();
    }
}
