package ua.edu.sumdu.j2se.kushnir.tasks.model;

import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

public class LinkedTaskList extends AbstractTaskList implements Cloneable {
    private Node headNode;
    int countOfElements = 0;
    private Node tailNode;


    private static class Node {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            if (task.equals(node.task) && nextNode == node.nextNode && prevNode == node.prevNode) return true;

            return task.equals(node.task) &&
                    nextNode != null || nextNode.equals(node.nextNode) &&
                    prevNode != null || prevNode.equals(node.prevNode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(task, nextNode, prevNode);
        }

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

    public Iterator<Task> iterator() {
        return new LinkedListIterator(this);
    }


    static class LinkedListIterator implements Iterator<Task> {
        private final LinkedTaskList list;
        private Node current;
        private Node nextAfterRemove;
        private int count;

        public LinkedListIterator(LinkedTaskList list) {
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return count < list.size();
        }

        @Override
        public Task next() throws IllegalStateException {
            count++;
            if (current == null && nextAfterRemove == null) {
                current = list.headNode;
            } else {
                if (nextAfterRemove != null) {
                    current = nextAfterRemove;
                    nextAfterRemove = null;
                    return current.task;
                }
                current = current.nextNode;
            }
            return current.task;
        }

        @Override
        public void remove() throws IllegalStateException {
            if (current == null) throw new IllegalStateException("Call remove without next!");
            nextAfterRemove = current.nextNode;
            if (list.remove(current.task)) {
                count--;
            }
        }
    }

    @Override
    public String toString() {
        return "LinkedTaskList:" +
                "headNode=" + headNode +
                ", countOfElements=" + countOfElements +
                ", tailNode=" + tailNode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(headNode, countOfElements, tailNode, headNode.nextNode.task, tailNode.prevNode.task);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkedTaskList that = (LinkedTaskList) o;
        if (countOfElements == that.countOfElements && headNode == that.headNode && tailNode == that.tailNode)
            return true;
        return countOfElements == that.countOfElements &&
                headNode != null || headNode.equals(that.headNode) &&
                tailNode != null || tailNode.equals(that.tailNode);
    }

    @Override
    public LinkedTaskList clone() {
        try {
            LinkedTaskList list = (LinkedTaskList) super.clone();
            list.headNode = null;
            list.tailNode = null;
            list.countOfElements = 0;
            for (int i = this.size() - 1; i >= 0; i--) {
                list.add(this.getTask(i).clone());
            }
            return list;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public Stream<Task> getStream() {
        Stream.Builder<Task> builder = Stream.builder();
        for (int i = 0; i < countOfElements; i++) {
            builder.add(getTask(i));
        }
        return builder.build();
    }
}
