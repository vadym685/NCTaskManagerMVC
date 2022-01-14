package ua.edu.sumdu.j2se.kushnir.tasks.view.views;

import ua.edu.sumdu.j2se.kushnir.tasks.model.AbstractTaskList;

import java.time.LocalDateTime;

public interface TaskView {

    void showList(AbstractTaskList list);

    void getCalendar(AbstractTaskList list);

    boolean getActivity();

    boolean getRepeatStatus();

    int getInterval();

    int getTaskIndex(AbstractTaskList list);

    String getTitle();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();

    LocalDateTime getTime();

}
