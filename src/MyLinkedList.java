public class MyLinkedList<E> {

    private static class Node<E> {
        E val;
        Node<E> next;

        Node(E val) { this.val = val; }
    }

    private final Node<E> head;
    private int size;

    // initialize head node
    public MyLinkedList() {
        this.head = new Node<>(null);
        head.next = null;

        this.size = 0;
    }

    public int size() { return size; }

    public boolean empty() { return size == 0; }

    // returns the value of the nth item (starting at 0 for first)
    public E valueAt(int index) {
        Node<E> p = getNode(index);
        return p.val;
    }

    // adds an item to the front of the list
    public void pushFront(E value) {
        Node<E> frontNode = new Node<>(value);
        Node<E> next = head.next;
        head.next = frontNode;
        frontNode.next = next;
        size++;
    }

    // adds an item at the end
    public void pushBack(E value) {
        Node<E> newLastNode = new Node<>(value);
        Node<E> lastNode = getNode(size-1);
        lastNode.next = newLastNode;
        size++;
    }

    // insert value at index, so current item at that index is pointed to by new item at index
    public void insert(int index, E value) {
        checkElementIndex(index);
        Node<E> insertNode = new Node<>(value);
        Node<E> nodeBeforeInsertion = getNode(index - 1);
        Node<E> curIndexNode = nodeBeforeInsertion.next;
        nodeBeforeInsertion.next = insertNode;
        insertNode.next = curIndexNode;
        size++;
    }

    // remove front item and return its value
    public E popFront() {
        Node<E> popNode = head.next;
        head.next = head.next.next;
        size--;
        return popNode.val;
    }

    // removes end item and returns its value
    public E popBack() {
        Node<E> popNode = getNode(size-1);
        Node<E> newLastNode = getNode(size-2);
        newLastNode.next = null;
        size--;
        return popNode.val;
    }

    // removes node at given index
    public void erase(int index) {
        checkElementIndex(index);
        Node<E> nodeBeforeErase = getNode(index - 1);
        nodeBeforeErase.next = nodeBeforeErase.next.next;
        size--;
    }

    // get value of front item
    public E front() {
        return head.next.val;
    }

    // get value of end item
    public E back() {
        Node<E> backNode = getNode(size-1);
        return backNode.val;
    }

    // get value of the node at nth position from the end of the list
    public E valueNFromEnd(int n) {
        checkElementIndex(size-n);
        Node<E> nodeNFromEnd = getNode(size-n);
        return nodeNFromEnd.val;
    }

    // reverses the list
    public void reverse() {
        Node<E> prev = null;
        Node<E> curr = head;
        while (curr != null) {
            Node<E> nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
    }

    // removes the first item in the list with this value
    public void removeValue(E value) {
        Node<E> prev = null;
        Node<E> curr = head;
        for (int i=0; i<size; i++) {
            if (curr.val == value) {
                prev.next = curr.next;
                size--;
                break;
            }
            prev = curr;
            curr = curr.next;
        }
    }

    private Node<E> getNode(int index) {
        checkElementIndex(index);
        Node<E> p = head.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p;
    }
    private boolean isElementIndex(int index){ return index >= 0 && index < size; }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }


    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 1; i <= 5; i++) {
            list.pushFront(i);
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.valueAt(i));
        }
    }
}
