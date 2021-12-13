package ua.edu.sumdu.j2se.kushnir.tasks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

public class ArrayTaskList extends AbstractTaskList implements Cloneable {
    private Task[] arrayTasks = new Task[10];
    int countOfElements = 0;

    /*
          The method adds the passed element to the array
          in the method we check whether the passed element is an object of class Task,
          on each iteration of a method call we add an element to a cell with an index which = countOfElements and we increase index by 1
          if the index = the length of the current array, copy the array to a new one increasing its length.
     */
    @Override
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
         The method removes the passed element from the array
         in the method we check whether the passed element is an object of class Task,
         we walk along the array and look for our element, zero it
         we copy all elements which are after deleted in our array
         reduce countOfElements by 1 for the correct further work of the add method
     */
    @Override
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
       The method returns the number of non-null elements in the array
     */
    @Override
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
        The method returns an element in the array by index
     */
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= arrayTasks.length) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return arrayTasks[index];
    }

    /*
        The method returns the list type
     */
    @Override
    ListTypes.types getListType() {
        return ListTypes.types.ARRAY;
    }

    @Override
    public Iterator<Task> iterator() {
        return new Iterator<Task>() {

            private int cursor = 0;
            private int last = -1;

            @Override
            public boolean hasNext() {
                if (countOfElements == 1) {
                    return false;
                }
                return cursor < size();
            }

            @Override
            public Task next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                int i = cursor;
                Task next = getTask(i);
                last = i;
                cursor = i + 1;
                return next;
            }

            @Override
            public void remove() {
                if (last < 0) {
                    throw new IllegalStateException();
                }
                ArrayTaskList.this.remove(getTask(last));
                if (last < cursor) {
                    cursor--;
                    last = -1;
                }
            }
        };
    }

    @Override
    public String toString() {
        return "ArrayTaskList\n" +
                Arrays.toString(arrayTasks);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(arrayTasks);
    }

    @Override
    public ArrayTaskList clone() throws CloneNotSupportedException {
        ArrayTaskList list = (ArrayTaskList) super.clone();
        list.arrayTasks = Arrays.copyOf(this.arrayTasks, this.arrayTasks.length);
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayTaskList arrayTaskList = (ArrayTaskList) o;
        return countOfElements == arrayTaskList.countOfElements && Arrays.equals(arrayTasks, arrayTaskList.arrayTasks);
    }

    @Override
    public Stream<Task> getStream() {
        return Arrays.stream(arrayTasks);
    }
}
