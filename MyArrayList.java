/* Generic class that simulates an array list, supporting dynamic resizing and basic operations */
class MyArrayList<T> {
    private Object[] elements; /* Array to store the elements */
    private int size; /* Number of elements currently in the list */

    /* Constructor to initialize the internal array with a default capacity of 10 */
    public MyArrayList() {
        elements = new Object[10];
        size = 0;
    }

    /* Method to add an element to the list */
    public void add(T element) {
        /* Check if the internal array needs resizing */
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element; /* Add the element and increment the size */
    }

    /* Method to remove an element from the list */
    public void remove(T element) {
        for (int i = 0; i < size; i++) {
            /* Check if the current element matches the element to be removed */
            if (elements[i].equals(element)) {
                /* Replace the removed element with the last element and decrease the size */
                elements[i] = elements[size - 1];
                elements[size - 1] = null;
                size--;
                return;
            }
        }
    }

    /* Method to get an element at a specific index */
    public T get(int index) {
        /* Check if the index is within bounds */
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return (T) elements[index]; /* Return the element at the specified index */
    }

    /* Method to get the number of elements in the list */
    public int size() {
        return size;
    }

    /* Private method to resize the internal array when it becomes full */
    private void resize() {
        Object[] newElements = new Object[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, elements.length); /* Copy elements to the new array */
        elements = newElements;
    }

    /* Method to convert the list to an array */
    public T[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(elements, 0, result, 0, size); /* Copy elements to a new array */
        return (T[]) result;
    }
}
