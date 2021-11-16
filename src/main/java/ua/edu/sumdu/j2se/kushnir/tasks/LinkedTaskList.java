package ua.edu.sumdu.j2se.kushnir.tasks;

public class LinkedTaskList {
    private Node headNode;
    int countOfElements = 0;
    private Node tailNode;

    private static class Node {
        private final Task task;
        private Node nextNode;
        private Node prevNode;

        public Node(Task task) {
            this.task = task;
        }
    }

    /*
        метод додає переданий елемент в кінець списку
    */
    public void add(Task task) {
        if (headNode == null) {
            headNode = new Node(task);
            tailNode = headNode;
        } else {

            Node currentNode = new Node(task);
            currentNode.prevNode = tailNode;
            if (headNode == tailNode) {
                headNode.nextNode = currentNode;
            } else {
                tailNode.nextNode = currentNode;
            }
            tailNode = currentNode;
        }
        countOfElements++;
    }

    /*
       метод видаляє переданий елемент з списку
       в методі перевіряєм чи переданий елемент є обьєктом класу Task,
       проходимось по списку і шукаєм наш елемент, проводимо операції з нодами
     */
    public boolean remove(Task task) throws NullPointerException {
        if (task != null) {
            Node currentNode = headNode;
            if (countOfElements == 0 && headNode != null) {
                return false;
            }

            for (int i = 0; i < countOfElements; i++) {
                if (currentNode.task.equals(task)) {
                    if (currentNode.nextNode != null) {
                        currentNode.nextNode.prevNode = currentNode.prevNode;
                    } else {
                        tailNode = currentNode.prevNode;
                    }
                    if (currentNode.prevNode != null) {
                        currentNode.prevNode.nextNode = currentNode.nextNode;
                    } else {
                        headNode = currentNode.nextNode;
                    }
                    currentNode.nextNode = null;
                    currentNode.prevNode = null;
                    countOfElements--;
                    return true;
                } else {
                    if (currentNode.nextNode == null) {
                        return false;
                    } else {
                        currentNode = currentNode.nextNode;
                    }
                }
            }
        } else {
            throw new NullPointerException("The task cannot be null");
        }
        return false;
    }

    /*
        метод повертає кількість елементів в списку
     */
    public int size() {
        return countOfElements;
    }

    /*
        метод повертає елемент в списку по індексу
     */
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if ((index < 0) || (index > countOfElements)) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        Node current = headNode;
        for (int i = 0; i < index; i++) {
            current = current.nextNode;
        }
        return current.task;
    }

    /*
       метод повертає linkedTaskList задач які будуть виконані хоча б раз у переданому проміжку
       проходимось по початковому масиву, якщо знайшли підходящий елемент, додаємо його в
       новий обьєкт класу linkedTaskList
    */
    public LinkedTaskList incoming(int from, int to) {
        LinkedTaskList linkedTaskList = new LinkedTaskList();
        Node current = headNode;
        while (current != null) {
            if (current.task != null && (current.task.nextTimeAfter(from) < to) && current.task.nextTimeAfter(from) > from && (current.task.nextTimeAfter(from) != -1)) {
                linkedTaskList.add(current.task);
            }
            current = current.nextNode;
        }
        return linkedTaskList;
    }
}
