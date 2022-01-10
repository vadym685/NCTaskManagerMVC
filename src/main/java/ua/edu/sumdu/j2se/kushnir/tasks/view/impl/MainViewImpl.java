package ua.edu.sumdu.j2se.kushnir.tasks.view.impl;

import ua.edu.sumdu.j2se.kushnir.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.kushnir.tasks.view.views.MainView;

import java.time.LocalDateTime;

public class MainViewImpl implements MainView {
    /**
     * Displays application main menu.
     */
    @Override
    public void showMenu() {

    }

    /**
     * Displays application edit menu.
     */
    @Override
    public void editMenu() {

    }

    /**
     * Displays task list menu.
     *
     * @param list - general task list
     */
    @Override
    public void showTaskList(AbstractTaskList list) {

    }

    /**
     * Displays task calendar menu.
     *
     * @param list - general task list
     */
    @Override
    public void showCalendar(AbstractTaskList list) {

    }

    /**
     * Displays user choice menu.
     *
     * @return true if user input equal to yes, false if equal to no
     */
    @Override
    public boolean checkUserChoice() {
        return false;
    }

    /**
     * Displays task title input menu.
     *
     * @return task title
     */
    @Override
    public String getTitle() {
        return null;
    }

    /**
     * Displays task repeat status input menu.
     *
     * @return true if task is repeated, false if task is not repeated
     */
    @Override
    public boolean getRepeatStatus() {
        return false;
    }

    /**
     * Displays task start time input menu.
     *
     * @return start time
     */
    @Override
    public LocalDateTime getStartTime() {
        return null;
    }

    /**
     * Displays task end time input menu.
     *
     * @return end time
     */
    @Override
    public LocalDateTime getEndTime() {
        return null;
    }

    /**
     * Display task interval input menu.
     *
     * @return interval
     */
    @Override
    public int getInterval() {
        return 0;
    }

    /**
     * Displays task time input menu.
     *
     * @return time
     */
    @Override
    public LocalDateTime getTime() {
        return null;
    }

    /**
     * Displays task index for action input menu.
     *
     * @param list - general task list
     * @return task index
     */
    @Override
    public int getIndex(AbstractTaskList list) {
        return 0;
    }

    /**
     * Displays task activity input menu.
     *
     * @return true if task is active, false if task is inactive
     */
    @Override
    public boolean getActivity() {
        return false;
    }
}
