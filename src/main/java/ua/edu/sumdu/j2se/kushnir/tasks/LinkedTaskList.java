package ua.edu.sumdu.j2se.kushnir.tasks;

public class LinkedTaskList extends AbstractTaskList {
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
        The method adds the passed element to the end of the list
    */
    @Override
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
        The method removes the passed item from the list
        in the method we check whether the passed element is an object of class Task,
        we go through the list and look for our element, we carry out operations with nodes
     */
    @Override
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
        The method returns the number of items in the list
     */
    @Override
    public int size() {
        return countOfElements;
    }

    /*
        The method returns an item in the index list
     */
    @Override
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
        The method returns the list type
    */
    @Override
    ListTypes.types getListType() {
        return ListTypes.types.LINKED;
    }
}
