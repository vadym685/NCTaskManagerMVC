package ua.edu.sumdu.j2se.kushnir.tasks.notification.controllers;

import ua.edu.sumdu.j2se.kushnir.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public interface CalendarNotificationController {
    void notify(SortedMap<LocalDateTime, Set<Task>> calendar);
}
