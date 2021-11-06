package ua.edu.sumdu.j2se.kushnir.tasks;

import java.util.Arrays;

public class ArrayTaskList {
    private Task[] arrayTasks = new Task[10];
    int countOfElements = 0;

    /*
         метод додає переданий елемент в масив
         в методі перевіряєм чи переданий елемент є обьєктом класу Task,
         на кожній ітерації виклику методу добаємо елемент в комірку з індексом який = countOfElements і збільшуємо index на 1
         якщо індекс = довжині поточного масиву, копіюєм масив в новий збільшуючи його довжину.
     */
    public void add(Task task) throws NullPointerException {
        if (task != null) {
            if (countOfElements == arrayTasks.length) {
                arrayTasks = Arrays.copyOf(arrayTasks, arrayTasks.length * 2);
            }
            arrayTasks[countOfElements] = task;
            countOfElements++;
        } else {
            throw new NullPointerException("The task cannot be null");
        }
    }

    /*
         метод видаляє переданий елемент з масиву
         в методі перевіряєм чи переданий елемент є обьєктом класу Task,
         проходимось по масиву і шукаєм наш елемент, зануляєм його
         копіюєм всі елементи які знаходяться після видаленого в наш масив
         зменшуєм countOfElements на 1 для коректної подальшої роботи методу add
     */
    public boolean remove(Task task) {
        if (task != null) {

            if (arrayTasks.length == 0) {
                return false;
            }

            for (int i = 0; i < arrayTasks.length - 1; i++) {
                if (arrayTasks[i].equals(task)) {
                    arrayTasks[i] = null;

                    System.arraycopy(arrayTasks, i + 1, arrayTasks, i, (arrayTasks.length - 1) - i);

                    countOfElements--;
                    return true;
                }
            }
        } else {
            throw new NullPointerException("The task cannot be null");
        }
        return false;
    }

    /*
        метод повертає кількість не null елементів в масиві
     */
    public int size() {
        int size = 0;
        for (Task task : arrayTasks) {
            if (task != null) {
                size = size + 1;
            }
        }
        return size;
    }

    /*
        метод повертає елемент в масиві по індексу
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= arrayTasks.length) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return arrayTasks[index];
    }

    /*
        метод повертає масив задач які будуть виконані хоча б раз у переданому проміжку
        проходимось по початковому масиву, якщо знайшли підходящий елемент, додаємо його в
        новий обьєкт класу ArrayTaskList
     */
    public ArrayTaskList incoming(int from, int to) {
        ArrayTaskList arrayTaskList = new ArrayTaskList();
        for (Task task : arrayTasks) {
            if (task != null && (task.nextTimeAfter(from) < to) && (task.nextTimeAfter(from) != -1)) {
                arrayTaskList.add(task);
            }
        }
        return arrayTaskList;
    }
}
