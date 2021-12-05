package ua.edu.sumdu.j2se.kushnir.tasks;

import java.util.Objects;

public class Task implements Cloneable {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repeated;

    // Constructor to create an inactive no repetitive task
    public Task(String title, int time) throws IllegalArgumentException {
        if (time < 0) {
            throw new IllegalArgumentException("time must be positive");
        }
        this.title = title;
        this.time = time;
        this.start = time;
        this.end = time;
        this.active = false;
        this.repeated = false;

    }

    // Constructor to create an inactive repetitive task
    public Task(String title, int start, int end, int interval) throws IllegalArgumentException {
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
    public int getTime() {
        if (repeated) {
            return start;
        } else {
            return time;
        }
    }

    // A method for changing the task execution time, if the task is repeated it will be converted to non-repeated
    public void setTime(int time) throws IllegalArgumentException {
        if (time < 0) {
            throw new IllegalArgumentException("time must be positive");
        }
        if (this.repeated) {
            this.repeated = false;
            this.start = 0;
            this.end = 0;
            this.interval = 0;
        }else {
            this.start = time;
            this.end = time;
        }
        this.time = time;

    }

    // A method for changing the task execution time, if the task is no repeated it will be converted to repeated
    public void setTime(int start, int end, int interval) throws IllegalArgumentException {
        if (interval < 0) {
            throw new IllegalArgumentException("interval must be > 0 ");
        }
        if (!repeated) {
            this.repeated = true;
            this.time = 0;
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    /*
     Method for accessing the task interval, if the task is no repeated, the execution
     time is returned, in other cases, the beginning of the task interval
    */
    public int getStartTime() {
        if (!repeated) {
            return time;
        } else {
            return start;
        }
    }

    // A method for changing the time interval of the task
    public void setStartTime(int start) {
        this.start = start;
    }

    /*
         Method for accessing the task interval, if the task is no repeated,
         the execution time is returned, in other cases the end of the task interval
     */
    public int getEndTime() {
        if (!repeated) {
            return time;
        } else {
            return end;
        }
    }

    // A method for changing the time interval of the task
    public void setEndTime(int end) {
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
    public int nextTimeAfter(int current) {
        if (!active) {
            return -1;
        } else {
            if (!repeated) {
                if (current >= time) {
                    return -1;
                }
                return time;
            } else {
                int newTime = -1;
                for (int i = start; i <= end; i = i + interval) {
                    if (current < i) {
                        newTime = i;
                        break;
                    }
                }
                return newTime;
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
                && start == task.getStartTime() && end == task.getEndTime()
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
