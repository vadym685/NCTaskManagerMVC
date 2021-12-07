package ua.edu.sumdu.j2se.kushnir.tasks;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Tasks {


    // метод що повертає підмножину задач, які заплановані на виконання хоча б раз після часу start і не пізніше ніж end
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime from, LocalDateTime to) throws IllegalArgumentException{

        if ((from == null)||(to == null) || tasks == null) {
            throw new IllegalArgumentException("Аргументи не можуть бути null!");
        }else {
            return StreamSupport.stream(tasks.spliterator(), false)
                    .filter(task -> task != null && task.nextTimeAfter(from).isAfter(to) && task.nextTimeAfter(from) != null).collect(Collectors.toList());
        }
    }

    // метод, який будує календар задач на заданий період – таблицю, де кожній даті
    //відповідає множина задач, що мають бути виконані в цей час.
    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime from, LocalDateTime to) throws IllegalArgumentException {

        if ((from == null)||(to == null) || tasks == null) {
            throw new IllegalArgumentException("Аргументи не можуть бути null!");
        }else {

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

}
