
import java.util.*;
import java.util.stream.Stream;

//C2=0
//C3=1
/**
 * Типізована колекція на основі однозв'язного списку.
 * Колекція реалізує інтерфейс List.
 *
 * @param <Tariff> Тип елементів колекції (узагальнений клас з лабораторної №5).
 */
public class MyLinkedList<Tariff> implements List<Tariff> {

    /**
     * Внутрішній клас, що представляє вузол однозв'язного списку.
     */
    private static class Node<Tariff> {
        /** Значення, що зберігається у вузлі. */
        Tariff data;

        /** Вказівник на наступний вузол. */
        Node<Tariff> next;

        /**
         * Конструктор вузла.
         *
         * @param data значення для збереження у вузлі
         */
        Node(Tariff data) {
            this.data = data;
        }
    }

    /** Головний вузол списку. */
    private Node<Tariff> head;

    /** Розмір списку. */
    private int size;

    /**
     * Порожній конструктор, що ініціалізує пустий список.
     */
    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Конструктор, що додає один елемент у список.
     *
     * @param element елемент для додавання
     */
    public MyLinkedList(Tariff element) {
        this();
        add(element);
    }

    /**
     * Конструктор, що додає всі елементи з колекції у список.
     *
     * @param collection колекція для додавання
     */
    public MyLinkedList(Collection<? extends Tariff> collection) {
        this();
        addAll(collection);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<Tariff> iterator() {
        return new Iterator<>() {
            private Node<Tariff> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Tariff next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Tariff data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int index = 0;
        for (Node<Tariff> current = head; current != null; current = current.next) {
            array[index++] = current.data;
        }
        return array;
    }

    @Override
    public <E> E[] toArray(E[] a) {
        if (a.length < size) {
            a = (E[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        int index = 0;
        Object[] result = a;
        for (Node<Tariff> current = head; current != null; current = current.next) {
            result[index++] = current.data;
        }
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(Tariff t) {
        Node<Tariff> newNode = new Node<>(t);
        if (head == null) {
            head = newNode;
        } else {
            Node<Tariff> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (head == null) {
            return false;
        }
        if (Objects.equals(head.data, o)) {
            head = head.next;
            size--;
            return true;
        }
        Node<Tariff> current = head;
        while (current.next != null && !Objects.equals(current.next.data, o)) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Tariff> c) {
        boolean modified = false;
        for (Tariff element : c) {
            modified |= add(element);
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends Tariff> c) {
        throw new UnsupportedOperationException("addAll at index is not supported.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object element : c) {
            modified |= remove(element);
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("retainAll is not supported.");
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public Tariff get(int index) {
        checkIndex(index);
        Node<Tariff> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public Tariff set(int index, Tariff element) {
        checkIndex(index);
        Node<Tariff> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        Tariff oldData = current.data;
        current.data = element;
        return oldData;
    }

    @Override
    public void add(int index, Tariff element) {
        throw new UnsupportedOperationException("add at index is not supported.");
    }

    @Override
    public Tariff remove(int index) {
        checkIndex(index);
        if (index == 0) {
            Tariff data = head.data;
            head = head.next;
            size--;
            return data;
        }
        Node<Tariff> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        Tariff data = current.next.data;
        current.next = current.next.next;
        size--;
        return data;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        for (Node<Tariff> current = head; current != null; current = current.next) {
            if (Objects.equals(current.data, o)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        int currentIndex = 0;
        for (Node<Tariff> current = head; current != null; current = current.next) {
            if (Objects.equals(current.data, o)) {
                index = currentIndex;
            }
            currentIndex++;
        }
        return index;
    }

    @Override
    public ListIterator<Tariff> listIterator() {
        throw new UnsupportedOperationException("listIterator is not supported.");
    }

    @Override
    public ListIterator<Tariff> listIterator(int index) {
        throw new UnsupportedOperationException("listIterator at index is not supported.");
    }

    @Override
    public List<Tariff> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("subList is not supported.");
    }
    /**
     * Перевіряє, чи знаходиться індекс у межах допустимого діапазону.
     * Якщо індекс є недійсним, генерується виняток {@code IndexOutOfBoundsException}.
     *
     * @param index індекс для перевірки
     * @throws IndexOutOfBoundsException якщо індекс менший за 0 або більший чи рівний розміру списку
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
    /**
     * Повертає потік елементів списку.
     *
     * @return потік елементів
     */
    public Stream<Tariff> stream() {
        List<Tariff> tempList = new ArrayList<>();
        for (Tariff element : this) {
            tempList.add(element);
        }
        return tempList.stream();
    }
    /**
     * Сортує список за допомогою компаратора.
     *
     * @param comparator компаратор для сортування
     */
    public void sort(Comparator<? super Tariff> comparator) {
        List<Tariff> tempList = new ArrayList<>();
        for (Tariff element : this) {
            tempList.add(element);
        }
        tempList.sort(comparator);

        // Очистити список і додати відсортовані елементи
        clear();
        for (Tariff element : tempList) {
            add(element);
        }
    }

}
