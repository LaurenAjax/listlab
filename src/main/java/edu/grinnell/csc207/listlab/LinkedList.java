package edu.grinnell.csc207.listlab;

/**
 * A linked implementation of the list ADT.
 */
public class LinkedList {

    /**
     * The place where all elements are stored
     *
     */
    private static class Node {
        public int element;
        public Node next;
    }

    private Node first;
    /* the first node in the list shared throughout */

    /**
     * Initialized the first node of the list
     *
     */
    public LinkedList() {
        first = null;
    }

    /**
     * Adds <code>value</code> to the end of the list
     *
     * @param value the value to add to the end of the list
     */
    public void add(int value) {
        if (first == null) {
            first = new Node();
            first.element = value;
            first.next = null;
        } else {
            Node currentNode;
            for (currentNode = first; !(currentNode.next == null); currentNode = currentNode.next) {
            }
            currentNode.next = new Node();
            currentNode.next.element = value;
            currentNode.next.next = null;
        }
    }

    /**
     * Determines the size of the list
     *
     * @return the number of elements in the list
     */
    public int size() {
        int size = 0;
        if (first == null) {
            return size;
        }
        for (Node currentNode = first; !(currentNode == null); currentNode = currentNode.next) {
            size++;
        }
        return size;
    }

    /**
     * Retrieves the element at the given index
     *
     * @param index the index of the element to retrieve
     * @return the value at the specified <code>index</code>
     */
    public int get(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        Node currentNode = first;
        for (int count = 0; count < index; count++) {
            currentNode = currentNode.next;
        }
        return currentNode.element;
    }

    /**
     * Removes the value at <code>index</code> from the list
     *
     * @param index the index of the element to remove
     * @return the element at <code>index</code>
     */
    public int remove(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int value;
        if (index == 0) {
            value = first.element;
            first = first.next;
            return value;
        }
        Node currentNode = first;
        for (int count = 1; count < index; count++) {
            currentNode = currentNode.next;
        }
        value = currentNode.next.element;
        currentNode.next = currentNode.next.next;
        return value;
    }

    /**
     * Returns whether or not the list is empty
     *
     * @return true if the first node of the list is null, false if not
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Sets the first node of the list to null, enabling the array to be
     * overwritten and the previous values unretrievable
     *
     */
    public void clear() {
        first = null;
    }

    /**
     * Finds the first occurrence of value in the list, if it exists
     *
     * @param value the value that is being searched for
     * @return the index of the value in the list
     */
    public int indexOf(int value) {
        int index = 0;
        for (Node currentNode = first; !(currentNode == null); currentNode = currentNode.next) {
            if (currentNode.element == value) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * Returns whether or not value is contained in the list
     *
     * @param value the value that is being searched for
     * @return true if the value is found, false otherwise
     */
    public boolean contains(int value) {
        for (Node currentNode = first; !(currentNode == null); currentNode = currentNode.next) {
            if (currentNode.element == value) {
                return true;
            }
        }
        return false;
    }

    /**
     * Inserts the given value at the given index in the list
     *
     * @param index the index of the list wherein the value is put
     * @param value the value that is put at the appropriate index in the list
     */
    public void insert(int index, int value) {
        Node save;
        if (index > size() || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size()) {
            add(value);
        }
        if (index == 0) {
            save = first;
            first = new Node();
            first.element = value;
            first.next = save;
        }
        Node currentNode = first;
        for (int i = 1; i < index; i++) {
            currentNode = currentNode.next;
        }
        save = currentNode.next;
        currentNode.next = new Node();
        currentNode.next.element = value;
        currentNode.next.next = save;
    }

    /**
     * Transforms the list into a string
     *
     * @return a string made up of the elements of the list in order
     */
    @Override
    public String toString() {
        String str = "[" + first.element;
        for (Node currentNode = first.next; !(currentNode == null); currentNode = currentNode.next) {
            str = str + ", " + currentNode.element;
        }
        str = str + "]";
        return str;
    }

    /**
     * Determines whether two lists are identical
     *
     * @param other the list to be compared
     * @return true if the lists are identical, false otherwise
     */
    public boolean equals(LinkedList other) {
        if (!(size() == other.size())) {
            return false;
        }
        for (int i = 0; i < size(); i++) {
            if (!(get(i) == other.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Combines two lists into one long one
     *
     * @param other the list to be added second
     * @return the new long list
     */
    public LinkedList concat(LinkedList other) {
        LinkedList newLink = new LinkedList();
        if (first == null) {
            newLink.first = other.first;
            return newLink;
        }
        newLink.first = first;
        Node currentNode;
        for (currentNode = newLink.first; !(currentNode.next == null); currentNode = currentNode.next) {
        }
        currentNode.next = other.first;
        return newLink;
    }
}
