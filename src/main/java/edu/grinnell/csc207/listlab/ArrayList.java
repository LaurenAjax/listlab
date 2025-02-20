package edu.grinnell.csc207.listlab;

/**
 * An array-based implementation of the list ADT.
 */
public class ArrayList {

    private int[] arr;
    /* the array that makes up the list */
    private int size;
    /* the integer that describes the size of the list */

    /**
     * Initializes the array list
     *
     */
    public ArrayList() {
        arr = new int[10];
        size = 0;
    }

    /**
     * Adds <code>value</code> to the end of the list
     *
     * @param value the value to add to the end of the list
     */
    public void add(int value) {
        if (size == arr.length) {
            int[] newArr = new int[2 * arr.length];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            arr = newArr;
        }
        arr[size] = value;
        size += 1;
    }

    /**
     * Determines the size of the list
     *
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Retrieves the element at the given index
     *
     * @param index the index of the element to retrieve
     * @return the value at the specified <code>index</code>
     */
    public int get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return arr[index];
    }

    /**
     * Removes the value at <code>index</code> from the list
     *
     * @param index the index of the element to remove
     * @return the element at <code>index</code>
     */
    public int remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int save = arr[index];
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
        return save;
    }

    /**
     * Returns whether or not the list is empty
     *
     * @return true if the size of the list is 0, false if not
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Sets the size of the list to 0, enabling the array to be overwritten and
     * the previous values unretrievable
     *
     */
    public void clear() {
        size = 0;
    }

    /**
     * Finds the first occurrence of value in the list, if it exists
     *
     * @param value the value that is being searched for
     * @return the index of the value in the list
     */
    public int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == value) {
                return i;
            }
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
        for (int i = 0; i < size; i++) {
            if (arr[i] == value) {
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
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            add(value);
        }
        if (size == arr.length) {
            int[] newArr = new int[2 * arr.length];
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            arr = newArr;
        }
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = value;
        size++;
    }

    /**
     * Transforms the list into a string
     *
     * @return a string made up of the elements of the list in order
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "";
        }
        String str = "[" + arr[0];
        for (int i = 1; i < size; i++) {
            str = str + ", " + arr[i];
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
    public boolean equals(ArrayList other) {
        if (!(size == other.size())) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!(arr[i] == other.get(i))) {
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
    public ArrayList concat(ArrayList other) {
        ArrayList newArr = new ArrayList();
        for (int i = 0; i < size; i++) {
            newArr.add(arr[i]);
        }
        for (int j = 0; j < other.size(); j++) {
            newArr.add(other.get(j));
        }
        return newArr;
    }
}
