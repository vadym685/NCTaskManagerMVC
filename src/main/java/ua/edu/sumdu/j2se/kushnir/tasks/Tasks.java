package ua.edu.sumdu.j2se.kushnir.tasks;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Tasks {

    /*
    Method that returns a subset of tasks that are scheduled to run at least once in an interval
    */
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime from, LocalDateTime to) {
        if ((from == null) || (to == null) || tasks == null) {
            throw new IllegalArgumentException(" Uncorrected argument 'from' or 'to' or 'tasks' ");
        }

        Iterator<Task> iterator = tasks.iterator();

        while (iterator.hasNext()) {
            Task currentTask = iterator.next();
            if (currentTask.nextTimeAfter(from) != null &&
                    (currentTask.nextTimeAfter(from).isBefore(to) ||
                            currentTask.nextTimeAfter(from).isEqual(to))) {
                continue;
            }
            iterator.remove();
        }
        return tasks;
    }

    /*
    build a calendar of tasks for a given period, where each date
    answer to the set of tasks to be executed at this time,
     */
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime from, LocalDateTime to) throws IllegalArgumentException {

        if ((from == null) || (to == null) || tasks == null) {
            throw new IllegalArgumentException(" Uncorrected argument 'from' or 'to' or 'tasks' ");
        }

        SortedMap<LocalDateTime, Set<Task>> calendarTask = new TreeMap<>();

        for (Task task : incoming(tasks, from, to)) {
            LocalDateTime nextDateInCalendar;
            for (nextDateInCalendar = task.nextTimeAfter(from); nextDateInCalendar.compareTo(to) <= 0; nextDateInCalendar = nextDateInCalendar.plusSeconds(task.getRepeatInterval())) {
                calendarTask.computeIfAbsent(nextDateInCalendar, k -> new HashSet<>()).add(task);
            }
        }
        return calendarTask;
    }
}
