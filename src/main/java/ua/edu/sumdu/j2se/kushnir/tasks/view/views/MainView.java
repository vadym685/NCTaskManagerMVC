package ua.edu.sumdu.j2se.kushnir.tasks.view.views;

import ua.edu.sumdu.j2se.kushnir.tasks.model.AbstractTaskList;

import java.time.LocalDateTime;

public interface MainView {

    void showMenu();

    void editMenu();

    void showTaskList(AbstractTaskList list);

    void showCalendar(AbstractTaskList list);

    boolean checkUserChoice();

    String getTitle();

    boolean getRepeatStatus();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();

    int getInterval();

    LocalDateTime getTime();

    int getIndex(AbstractTaskList list);

    boolean getActivity();
}
