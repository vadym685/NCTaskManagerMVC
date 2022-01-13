package ua.edu.sumdu.j2se.kushnir.tasks.view.views;

import ua.edu.sumdu.j2se.kushnir.tasks.model.AbstractTaskList;

import java.time.LocalDateTime;

public interface TaskViev {
    void showList(AbstractTaskList list);

    void getCalendar(AbstractTaskList list);

    String getTitle();

    boolean getRepeatStatus();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();

    int getInterval();

    LocalDateTime getTime();

    int getTaskIndex(AbstractTaskList list);

    boolean getActivity();
}
