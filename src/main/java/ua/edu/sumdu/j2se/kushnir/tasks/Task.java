package ua.edu.sumdu.j2se.kushnir.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Cloneable, Serializable {
    private String title;
    private LocalDateTime time;
    private LocalDateTime start;
    private LocalDateTime end;
    private int interval;
    private boolean active;
    private boolean repeated;

    // Constructor to create an inactive no repetitive task
    public Task(String title, LocalDateTime time) throws IllegalArgumentException {
        if (time == null) {
            throw new IllegalArgumentException("time must be not null");
        }
        this.title = title;
        this.time = time;
        this.start = time;
        this.end = time;
        this.active = false;
        this.repeated = false;

    }

    // Constructor to create an inactive repetitive task
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) throws IllegalArgumentException {
        if (interval < 0) {
            throw new IllegalArgumentException("interval must be > 0 ");
        }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.active = false;
        this.repeated = true;

    }

    // Method for accessing the task name
    public String getTitle() {
        return title;
    }

    // Метод для зміни назви задачі
    public void setTitle(String title) {
        this.title = title;
    }

    /*
     Method for accessing the task execution time, if the task is repeated, the beginning
     of the task execution time interval will return, in other cases the execution time
    */
    public LocalDateTime getTime() {
        if (repeated) {
            return start;
        } else {
            return time;
        }
    }

    // A method for changing the task execution time, if the task is repeated it will be converted to non-repeated
    public void setTime(LocalDateTime time) throws IllegalArgumentException {
        if (time == null) {
            throw new IllegalArgumentException("time must be not null");
        }
        if (this.repeated) {
            this.repeated = false;
            this.start = null;
            this.end = null;
            this.interval = 0;
        } else {
            this.start = time;
            this.end = time;
        }
        this.time = time;

    }

    // A method for changing the task execution time, if the task is no repeated it will be converted to repeated
    public void setTime(LocalDateTime start, LocalDateTime end, int interval) throws IllegalArgumentException {
        if (interval < 0) {
            throw new IllegalArgumentException("interval must be > 0 ");
        }
        if (!repeated) {
            this.repeated = true;
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    /*
     Method for accessing the task interval, if the task is no repeated, the execution
     time is returned, in other cases, the beginning of the task interval
    */
    public LocalDateTime getStartTime() {
        if (!repeated) {
            return time;
        } else {
            return start;
        }
    }

    // A method for changing the time interval of the task
    public void setStartTime(LocalDateTime start) {
        this.start = start;
    }

    /*
         Method for accessing the task interval, if the task is no repeated,
         the execution time is returned, in other cases the end of the task interval
     */
    public LocalDateTime getEndTime() {
        if (!repeated) {
            return time;
        } else {
            return end;
        }
    }

    // A method for changing the time interval of the task
    public void setEndTime(LocalDateTime end) {
        this.end = end;
    }

    // The method for accessing the interval with which the task is repeated
    public int getInterval() {
        return interval;
    }

    // A method for changing the interval at which the task is repeated
    public void setInterval(int interval) throws IllegalArgumentException {
        if (interval < 0) {
            throw new IllegalArgumentException("interval must be > 0 ");
        }
        this.interval = interval;
    }

    // A method for accessing a field that indicates whether the task is active / inactive
    public boolean isActive() {
        return active;
    }

    // A method for changing a field that indicates whether a task is active / inactive
    public void setActive(boolean active) {
        this.active = active;
    }

    // A method for accessing a field that indicates whether the task is repetitive / non-repetitive
    public boolean isRepeated() {
        return repeated;
    }

    // A method for changing a field that indicates whether a task is repetitive / non-repetitive
    public void setRepeated(boolean repeated) {
        this.repeated = repeated;
    }

    // The method for accessing the interval with which the task is repeated, if the task is not repeated, it returns 0, in other cases the interval
    public int getRepeatInterval() {
        if (repeated) {
            return interval;
        } else {
            return 0;
        }
    }

    // A method for finding the next task execution time
    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (!active) {
            return null;
        } else {
            if (!repeated) {
                if (current.isAfter(time) || current.isEqual(time)) {
                    return null;
                }
                return time;
            } else {
                for (LocalDateTime i = start; i.isBefore(end) || i.isEqual(end); i = i.plusSeconds(interval)) {
                    if (current.isBefore(i)) {
                        return i;
                    }
                }

                return null;
            }
        }
    }

    @Override
    public String toString() {

        return "Task:" +
                "title='" + title + '\'' +
                ", time=" + time +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", active=" + active +
                ", repeated=" + repeated;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getTime(), getStartTime(), getEndTime(), getInterval(), isActive(), isRepeated());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;

        return time == task.getTime()
                && start.isEqual(task.getStartTime()) && end.isEqual(task.getEndTime())
                && interval == task.getInterval() && active == task.isActive()
                && repeated == task.isRepeated() && Objects.equals((title), task.getTitle());
    }

    @Override
    public Task clone() {
        try {
            return (Task) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
