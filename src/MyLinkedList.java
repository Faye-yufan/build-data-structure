import java.util.NoSuchElementException;

public class MyLinkedList<E> {

    private static class Node<E> {
        E val;
        Node<E> next;

        Node(E val) { this.val = val; }
    }

    private Node<E> head, tail;
    private int size;

    // initialize head node
    public MyLinkedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = null;

        this.size = 0;
    }

    public int size() { return size; }

    public boolean empty() { return size == 0; }

    // adds an item to the front of the list
    public void pushFront(E value) {
        Node<E> x = new Node<>(value);
        Node<E> next = head.next;
        head.next = x;
        x.next = next;
        size++;
    }

    // adds an item at the end
    public void pushBack(E value) {
        Node<E> newLastNode = new Node<>(value);
        Node<E> lastNode;
        if (size - 1 >= 0){
            lastNode = getNode(size - 1);
        } else {
            lastNode = head;
        }
        lastNode.next = newLastNode;
        newLastNode.next = tail;
        size++;
    }

    // insert value at index, so current item at that index is pointed to by new item at index
    public void insert(int index, E value) {
        checkPositionIndex(index);

        // special case
        if (index == size) {
            pushBack(value);
            return;
        }

        Node<E> insertNode = new Node<>(value);
        Node<E> nodeBeforeInsertion;
        if (index - 1 >= 0) {
            nodeBeforeInsertion = getNode(index - 1);
        } else {
            nodeBeforeInsertion = head;
        }

        Node<E> curIndexNode = nodeBeforeInsertion.next;
        nodeBeforeInsertion.next = insertNode;
        insertNode.next = curIndexNode;
        size++;
    }

    // remove front item and return its value
    public E popFront() {
        if (empty()) {
            throw new NoSuchElementException();
        }
        Node<E> popNode = head.next;
        head.next = head.next.next;
        popNode.next = null; // remember to set this to null
        size--;
        return popNode.val;
    }

    // removes end item and returns its value
    public E popBack() {
        if (empty()) {
            throw new NoSuchElementException();
        }

        Node<E> popNode = getNode(size-1);
        Node<E> newLastNode;
        if (size - 2 >= 0) {
            newLastNode = getNode(size-2);
        } else {
            newLastNode = head;
        }
        newLastNode.next = tail;
        popNode.next = null;
        size--;
        return popNode.val;
    }

    // removes node at given index
    public void erase(int index) {
        checkElementIndex(index);

        Node<E> nodeBeforeErase;
        if (index - 1 >= 0) {
            nodeBeforeErase = getNode(index - 1);
        } else {
            nodeBeforeErase = head;
        }

        Node<E> eraseNode = nodeBeforeErase.next;
        nodeBeforeErase.next = nodeBeforeErase.next.next;
        eraseNode.next = null;
        size--;
    }

    // returns the value of the nth item (starting at 0 for first)
    public E valueAt(int index) {
        Node<E> p = getNode(index);
        return p.val;
    }

    // get value of front item
    public E front() {
        if (empty()) {
            throw new NoSuchElementException();
        }
        return head.next.val;
    }

    // get value of end item
    public E back() {
        if (empty()) {
            throw new NoSuchElementException();
        }
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
        Node<E> prev = head;
        Node<E> curr = head.next;
        while (curr != null) {
            Node<E> nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        Node<E> temp = head;
        head = new Node<> (null);
        head.next = prev;
        tail = temp;
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
    private boolean isPositionIndex(int index){ return index >= 0 && index <= size; }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }


    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }


    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        for (int i = 1; i <= 5; i++) {
            list.pushFront(i);
        }
        list.reverse();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.valueAt(i));
        }
    }
}
