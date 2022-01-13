package ua.edu.sumdu.j2se.kushnir.tasks;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.kushnir.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kushnir.tasks.model.Task;
import ua.edu.sumdu.j2se.kushnir.tasks.model.Tasks;
import ua.edu.sumdu.j2se.kushnir.tasks.notification.controllers.CalendarNotificationController;
import ua.edu.sumdu.j2se.kushnir.tasks.notification.impl.CalendarNotificationImpl;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class NotificationManager extends Thread{

    private static final Logger log = Logger.getLogger(NotificationManager.class);
    private static final long TIME = 300_000; // -> 3 minutes
    private static final long MINUTES = 60;
    private final AbstractTaskList atl;
    private final CalendarNotificationController notification;

    public NotificationManager(AbstractTaskList list) {
        atl = list;
        notification = CalendarNotificationImpl.getInstance();
    }


    @Override
    public void run() {
        SortedMap<LocalDateTime, Set<Task>> calendar;
        while (true) {
            calendar = Tasks.calendar(atl,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(MINUTES));
            if (!calendar.isEmpty()) {
                notification.notify(calendar);
            }
            try {
                Thread.sleep(TIME);
            } catch (InterruptedException e) {
                log.error(e);
                System.out.println(e.getMessage());
            }
        }
    }
}
