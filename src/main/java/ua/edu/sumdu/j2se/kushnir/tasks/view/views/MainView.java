package ua.edu.sumdu.j2se.kushnir.tasks.view.views;

import ua.edu.sumdu.j2se.kushnir.tasks.model.AbstractTaskList;

import java.time.LocalDateTime;

public interface MainView {

    void showMenu();

    void editMenu();

    void showTaskList(AbstractTaskList list);

    void showCalendar(AbstractTaskList list);

    boolean checkUserChoice();

    boolean getRepeatStatus();

    boolean getActivity();

    int getInterval();

    int getIndex(AbstractTaskList list);

    String getTitle();

    LocalDateTime getStartTime();

    LocalDateTime getEndTime();

    LocalDateTime getTime();
}
