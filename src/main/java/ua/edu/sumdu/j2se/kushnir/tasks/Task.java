package ua.edu.sumdu.j2se.kushnir.tasks;

import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

public class Task {
    private String title;
    private int time;
    private int start;
    private int end;
    private int interval;
    private boolean active;
    private boolean repeated;

    // конструктор для створення неактивної неповторюваної задачі
    public Task(String title, int time) {
        this.title = title;
        this.time = time;
        this.start = time;
        this.end = time;
        this.active = false;
        this.repeated = false;
    }

    // конструктор для створення неактивної повторюваної задачі
    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        this.active = false;
        this.repeated = true;
    }

    // гетер для доступу до назви задачі
    public String getTitle() {
        return title;
    }

    // сетер для зміни назви задачі
    public void setTitle(String title) {
        this.title = title;
    }

    // гетер для доступу до часу виконання задачі, якщо таск повторюваний повернеться початок проміжку часу виконання задачі, в інших випадках  час вивиконання
    public int getTime() {
        if (repeated) {
            return start;
        } else {
            return time;
        }
    }

    // сетер для зміни часу виконання задачі, якщо таск повторюваний він буде перетворений на неповторюваний
    public void setTime(int time) {
        if (this.repeated) {
            this.repeated = false;
            this.start = 0;
            this.end = 0;
            this.interval = 0;
        }
        this.time = time;
    }

    // сетер для зміни часу виконання задачі, якщо таск неповторюваний він буде перетворений на повторюваний
    public void setTime(int start, int end, int interval) {
        if (!repeated) {
            this.repeated = true;
            this.time = 0;
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }
    // гетер для доступу до проміжку виконання задачі, якщо таск неповторюваний повернеться час вивиконання, в інших випадках початок проміжку часу виконання задачі
    public int getStartTime() {
        if (!repeated) {
            return time;
        } else {
            return start;
        }
    }

    public void setStartTime(int start) {
        this.start = start;
    }

    public int getEndTime() {
        if (!repeated) {
            return time;
        } else {
            return end;
        }
    }

    public void setEndTime(int end) {
        this.end = end;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isRepeated() {
        return repeated;
    }

    public void setRepeated(boolean repeated) {
        this.repeated = repeated;
    }

    public int getRepeatInterval() {
        if (repeated) {
            return interval;
        } else {
            return 0;
        }
    }

    public int nextTimeAfter(int current) {
        if (!active) {
            return -1;
        } else {
            if (!repeated) {
                if (current >= time) {
                    return -1;
                } else {
                    return time;
                }

            } else {
                int newTime = -1;
                for (int i = start; i < end; i = i + interval) {
                    if (current < i) {
                        newTime = i;
                        break;
                    }
                }
                return newTime;
            }
        }
    }
}
