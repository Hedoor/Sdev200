import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<E> implements MyList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public TwoWayLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public TwoWayLinkedList(E[] elements) {
        for (E element : elements) {
            add(element);
        }
    }

    @Override
    public void add(E e) {
        Node<E> newNode = new Node<>(e);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.previous = tail;
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size) {
            add(e);
            return;
        }

        Node<E> newNode = new Node<>(e);
        if (index == 0) {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        } else {
            Node<E> current = getNode(index);
            Node<E> previousNode = current.previous;

            newNode.next = current;
            newNode.previous = previousNode;

            previousNode.next = newNode;
            current.previous = newNode;
        }

        size++;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return getNode(index).element;
    }

    @Override
    public int indexOf(E e) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (e.equals(current.element)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        Node<E> current = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (e.equals(current.element)) {
                return i;
            }
            current = current.previous;
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> current = getNode(index);
            Node<E> previousNode = current.previous;
            Node<E> nextNode = current.next;

            previousNode.next = nextNode;
            nextNode.previous = previousNode;

            size--;
            return current.element;
        }
    }

    @Override
    public E set(int index, E e) {
        checkIndex(index);
        Node<E> node = getNode(index);
        E oldElement = node.element;
        node.element = e;
        return oldElement;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e) >= 0;
    }

    @Override
    public boolean containsAll(MyList<E> otherList) {
        for (int i = 0; i < otherList.size(); i++) {
            if (!contains(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

       @Override
    public Iterator<E> iterator() {
        return new TwoWayLinkedListIterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new TwoWayLinkedListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return new TwoWayLinkedListIterator(index);
    }

    private Node<E> getNode(int index) {
        checkIndex(index);
        Node<E> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.previous;
            }
        }
        return current;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private E removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Node<E> oldHead = head;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.previous = null;
        }
        size--;
        return oldHead.element;
    }

    private E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Node<E> oldTail = tail;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.previous;
            tail.next = null;
        }
        size--;
        return oldTail.element;
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E e) {
            element = e;
        }
    }

    private class TwoWayLinkedListIterator implements ListIterator<E> {
        private Node<E> current;
        private int nextIndex;

        public TwoWayLinkedListIterator() {
            current = head;
            nextIndex = 0;
        }

        public TwoWayLinkedListIterator(int index) {
            current = getNode(index);
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = current.element;
            current = current.next;
            nextIndex++;
            return element;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            if (current == null) {
                current = tail;
            } else {
                current = current.previous;
            }
            E element = current.element;
            nextIndex--;
            return element;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E e) {
            if (current == null) {
                throw new IllegalStateException();
            }
                current.element = e;
        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }
}
