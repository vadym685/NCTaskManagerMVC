package ua.edu.sumdu.j2se.kushnir.tasks.notification.impl;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.kushnir.tasks.model.Task;
import ua.edu.sumdu.j2se.kushnir.tasks.notification.controllers.CalendarNotificationController;
import ua.edu.sumdu.j2se.kushnir.tasks.view.util.Output;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class CalendarNotificationImpl implements CalendarNotificationController {

    private static final Logger log = Logger.getLogger(CalendarNotificationImpl.class);
    private static CalendarNotificationImpl instance;

    public static CalendarNotificationImpl getInstance() {
        if (instance == null) {
            instance = new CalendarNotificationImpl();
        }
        return instance;
    }

    /**
     * Displays upcoming tasks to the user.
     *
     * @param calendar - general task calendar to notify
     */
    @Override
    public void notify(SortedMap<LocalDateTime, Set<Task>> calendar) {
        log.info("Execute notify command");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        Output.printNotification();

        for (Map.Entry<LocalDateTime, Set<Task>> entry : calendar.entrySet()) {
            log.info("Getting calendar date for notify");
            String date = entry.getKey().format(formatter);
            Output.notifyCalendarDate(date);
            for (Task t : entry.getValue()) {
                log.info("Getting calendar tasks by date: " + date);
                Output.notifyCalendarTask(t.getTitle());
            }
            System.out.println("");
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        log.info("Notify command is completed");
    }
}
