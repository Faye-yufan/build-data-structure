import java.util.NoSuchElementException;

public class MyQueueArr<E> {

    private int size;
    private E[] data;
    private final static int INIT_CAP = 1;

    private int first, last;

    public MyQueueArr(int initCap) {
        size = 0;
        data = (E[]) new Object[initCap];

        first = last = 0;
    }

    public MyQueueArr() {
        this(INIT_CAP);
    }

    public void enqueue(E e) {
        if (size == data.length) {
            resize(size * 2);
        }

        data[last] = e;
        last++;
        if (last == data.length) {
            last = 0;
        }

        size++;
    }

    public E dequeue() {
        if (empty()) {
            throw new NoSuchElementException();
        }

        if (size == data.length / 4) {
            resize(data.length / 2);
        }

        E deletedVal = data[first];
        data[first] = null;
        first++;
        if (first == data.length) {
            first = 0;
        }

        size--;
        return deletedVal;
    }

    public boolean empty() {
        return size == 0;
    }

    public boolean full() {
        return size == data.length;
    }

    private void resize(int newCap) {
        E[] temp = (E[]) new Object[newCap];

        for (int i = 0; i < size; i++) {
            temp[i] = data[(first + i) % data.length];
        }

        first = 0;
        last = size;
        data = temp;
    }
}
