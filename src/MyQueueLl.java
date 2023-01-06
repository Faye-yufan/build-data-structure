public class MyQueueLl<E> {

    private MyLinkedList<E> list = new MyLinkedList<>();

    // adds value at position at tail
    public void enqueue(E e) {
        list.pushBack(e);
    }

    // returns value and removes least recently added element (front)
    public E dequeue() {
        return list.popFront();
    }

    public boolean empty() {
        return list.empty();
    }

    public static void main(String[] args) {
        MyQueueLl<Integer> queue = new MyQueueLl<>();
        for (int i=0; i < 5; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue);

        for (int i = 0; i < 5; i++) {
            System.out.println(queue.dequeue());
        }
        System.out.println(queue.empty());
    }
}
