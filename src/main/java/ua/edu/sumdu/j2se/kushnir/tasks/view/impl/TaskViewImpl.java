package ua.edu.sumdu.j2se.kushnir.tasks.view.impl;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import ua.edu.sumdu.j2se.kushnir.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kushnir.tasks.model.Task;
import ua.edu.sumdu.j2se.kushnir.tasks.model.Tasks;
import ua.edu.sumdu.j2se.kushnir.tasks.view.util.Output;
import ua.edu.sumdu.j2se.kushnir.tasks.view.util.TaskTime;
import ua.edu.sumdu.j2se.kushnir.tasks.view.views.TaskView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;

public class TaskViewImpl implements TaskView {

    private static final Logger log = Logger.getLogger(TaskViewImpl.class);
    private final Scanner scanner;

    public TaskViewImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void showList(@NotNull AbstractTaskList list) {
        log.info("Showing all task list");
        if (list.size() == 0) {
            System.out.println(Output.EMPTY_LIST);
            log.info("List is empty");
        } else {
            getList(list);
        }
    }

    @Override
    public void getCalendar(AbstractTaskList list) {
        log.info("Getting task calendar");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        while (true) {
            LocalDateTime start = getStartTime();
            LocalDateTime end = getEndTime();

            if (start.isBefore(end)) {
                SortedMap<LocalDateTime, Set<Task>> calendar =
                        Tasks.calendar(list, start, end);

                if (!calendar.isEmpty()) {
                    System.out.println(Output.CALENDAR_HEAD);
                    for (Map.Entry<LocalDateTime, Set<Task>> entry : calendar.entrySet()) {
                        Output.printlnCalendarDate(entry.getKey().format(formatter));
                        for (Task t : entry.getValue()) {
                            Output.printlnCalendarTask(t.getTitle());
                        }
                        System.out.println("");
                    }
                    System.out.println(Output.CALENDAR_BOTTOM);
                } else {
                    log.info("Calendar is empty");
                    System.out.println("Calendar is empty");
                }
                break;
            } else {
                log.info("Wrong date input, trying again");
                System.out.println("Error. Enter start and end date correctly");
            }
        }
    }

    @Override
    public boolean getRepeatStatus() {
        log.info("Getting repeated status");
        String repeat = "";
        boolean repeatStatus = false;
        System.out.print("Input task repeat status (true:false): ");
        while (repeat.isEmpty()
                || (!repeat.equalsIgnoreCase("true")
                && !repeat.equalsIgnoreCase("false"))) {
            repeat = scanner.nextLine();
            if (repeat.isEmpty() || (!repeat.equalsIgnoreCase("true")
                    && !repeat.equalsIgnoreCase("false"))) {
                System.out.print("Try again. Repeat status (true:false): ");
            } else {
                repeatStatus = Boolean.parseBoolean(repeat.toLowerCase());
            }
        }
        return repeatStatus;
    }

    @Override
    public boolean getActivity() {
        log.info("Getting task activity");
        String active = "";
        boolean activeStatus = false;
        System.out.print("Input task activity status (true:false): ");
        while (active.isEmpty()
                || (!active.equalsIgnoreCase("true")
                && !active.equalsIgnoreCase("false"))) {
            active = scanner.nextLine();
            if (active.isEmpty() || (!active.equalsIgnoreCase("true")
                    && !active.equalsIgnoreCase("false"))) {
                System.out.print("Try again. Activity status (true:false): ");
            } else {
                activeStatus = Boolean.parseBoolean(active.toLowerCase());
            }
        }
        return activeStatus;
    }

    @Override
    public int getInterval() {
        log.info("Getting interval");
        int interval;
        System.out.print("Enter the task repetition interval in minutes: ");
        while (true) {
            interval = Integer.parseInt(scanner.nextLine());
            if (interval > 0) {
                return interval * 60;
            } else {
                System.out.print("Try again. Enter interval in minutes: ");
            }
        }
    }

    @Override
    public int getTaskIndex(@NotNull AbstractTaskList list) {
        log.info("Getting index for action");
        int id = 0;
        if (list.size() == 0) {
            System.out.println(Output.EMPTY_LIST);
        } else {
            showList(list);
            System.out.print("Enter the task id: ");
            while (true) {
                id = scanner.nextInt();
                scanner.nextLine();
                if (id > 0 && id <= list.size()) return id;
                System.out.print("Try again. Enter task id: ");
            }
        }
        return id;
    }

    @Override
    public String getTitle() {
        log.info("Getting task title");
        String title = "";
        System.out.print("Input task title: ");
        while (title.isEmpty()) {
            title = scanner.nextLine();
            if (title.isEmpty()) {
                System.out.print("Try again. Task title: ");
            }
        }
        return title;
    }

    @Override
    public LocalDateTime getStartTime() {
        log.info("Getting start time");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String date;
        LocalDateTime start;
        System.out.print("Enter start date (dd-MM-yyyy HH:mm): ");
        while (true) {
            try {
                date = scanner.nextLine();
                start = LocalDateTime.parse(date, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.print("Error. Try input start date again (dd-MM-yyyy HH:mm): ");
                log.error(e);
            }
        }
        return start;
    }

    @Override
    public LocalDateTime getEndTime() {
        log.info("Getting end time");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String date;
        LocalDateTime end;
        System.out.print("Enter end date (dd-MM-yyyy HH:mm): ");
        while (true) {
            try {
                date = scanner.nextLine();
                end = LocalDateTime.parse(date, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.print("Error. Try input end date again (dd-MM-yyyy HH:mm): ");
                log.error(e);
            }
        }
        return end;
    }

    @Override
    public LocalDateTime getTime() {
        log.info("Getting time");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String date;
        LocalDateTime time;
        System.out.print("Enter task date (dd-MM-yyyy HH:mm): ");
        while (true) {
            try {
                date = scanner.nextLine();
                time = LocalDateTime.parse(date, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.print("Error. Try input task date again (dd-MM-yyyy HH:mm): ");
                log.error(e);
            }
        }
        return time;
    }

    private void getList(@NotNull AbstractTaskList list) {
        log.info("Getting not empty task list");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        StringBuilder repeatedStringBuilder = new StringBuilder();
        StringBuilder notRepeatedStringBuilder = new StringBuilder();

        int i = 1;
        for (Task t : list) {
            String title = t.getTitle();
            String time = t.getTime().format(formatter);
            String start = t.getStartTime().format(formatter);
            String end = t.getEndTime().format(formatter);
            String interval = TaskTime.intervalFormatter(t.getRepeatInterval());
            String active = t.isActive() ? "active" : "inactive";

            if (t.isRepeated()) {
                log.info("Setting repeated task to general list");
                repeatedStringBuilder.append(i++).append("\t")
                        .append(title).append("\t\t")
                        .append(start).append("\t")
                        .append(end).append("\t")
                        .append(interval).append("\t")
                        .append(active).append("\n");
            } else {
                log.info("Setting not repeated task to general list");
                notRepeatedStringBuilder.append(i++).append("\t")
                        .append(title).append("\t\t")
                        .append(time).append("\t")
                        .append(active).append("\n");
            }
        }
        showRepeated(repeatedStringBuilder);
        showNotRepeated(notRepeatedStringBuilder);
    }

    private void showRepeated(@NotNull StringBuilder repeated) {
        log.info("Printing repeated task list");
        System.out.println(Output.HEAD_REPEATED_TASK);
        System.out.print(repeated.toString());
    }

    private void showNotRepeated(@NotNull StringBuilder notRepeated) {
        log.info("Printing not repeated task list");
        System.out.println(Output.HEAD_NOT_REPEATED_TASK);
        System.out.print(notRepeated.toString());
    }

}
