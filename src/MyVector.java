import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyVector<E> {

    public MyVector(int initCapacity) {
        data = (E[]) new Object[initCapacity];
        size = 0;
    }
    // initiate the array
    private E[] data;
    // record how many elements are inside this array
    private int size;

    public int size() {
        return size;
    }

    public int capacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // get the item at given index
    public E at(int index) {
        // check if the index is greater than size
        checkElementIndex(index);

        return data[index];
    }

    // add an element on the end of the array
    public void push(E e){
        if (data.length == size) {
            // add capacity
            resize(data.length * 2);
        }

        data[size] = e;
        size++;
    }

    public void insert(int index, E e) {
        checkPositionIndex(index);

        int cap = data.length;
        if (size == cap) {
            resize(2 * cap);
        }

        // move data[index...] --> data[index+1...]
        System.arraycopy(data, index,
                data, index+1,
                size - index);
        data[index] = e;
        size++;
    }

    public void prepend(E e) {
        insert(0, e);
    }

    public E pop() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int cap = data.length;
        // save some capacity
        if (size == cap / 4) {
            resize(cap / 2);
        }

        E lastVal = data[size - 1];
        // delete the last value
        data[size - 1] = null;
        size--;
        return lastVal;
    }

    //  delete(index) - delete item at index, shifting all trailing elements left
    public void delete(int index) {
        checkPositionIndex(index);

        int cap = data.length;
        if (size == cap / 4) {
            resize(cap / 2);
        }

        // move data[index...] --> data[index-1...]
        System.arraycopy(data, index + 1,
                data, index,
                size - index - 1);
        data[size - 1] = null;
        size--;
    }

    // remove(item) - looks for value and removes index holding it (even if in multiple places)
    public void remove(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                delete(i);
            }
        }
    }

    // find(item) - looks for value and returns first index with that value, -1 if not found
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    // change the capacity of the array
    private void resize(int newCap) {
        if (size > newCap) {
            return;
        }
        E[] temp = (E[]) new Object[newCap];

        for (int i = 0; i < size; i++) {
            temp[i] = data[i];
        }
        data = temp;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    // check if there is an element on this index
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    // check if we can add element on this index
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    public static void main(String[] args) {
        MyVector<Integer> arr = new MyVector<>(3);

        for (int i = 1; i <= 5; i++) {
            arr.push(i);
        }

        arr.delete(3);
        arr.insert(1, 9);
        arr.prepend(100);
        int val = arr.pop();

        for (int i = 0; i < arr.size(); i++) {
            System.out.println(arr.at(i));
        }
    }
}
