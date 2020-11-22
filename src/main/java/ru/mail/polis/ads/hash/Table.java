package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Table<K, V> implements HashTable<K, V> {

    private Cell<K, V>[] array;

    private int capacity;
    private int size = 0;
    private double FILL_FACTOR;

    private static class Cell<K, V> {
        private final K key;
        private V value;
        private Cell<K, V> next;

        public Cell(K key, V value, Cell<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public Table(int capacity, double fillFactory) {
        this.capacity = capacity;
        array = new Cell[capacity];
        FILL_FACTOR = fillFactory;
    }

    public Table() {
        FILL_FACTOR = 0.75;
        capacity = 16;
        array = new Cell[capacity];
    }

    private int index(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private void resize() {
        capacity *= 2;
        Cell<K, V>[] oldArray = array;
        array = new Cell[capacity];
        size = 0;
        for (Cell<K, V> start :
                oldArray) {
            Cell<K, V> current = start;
            while (current!= null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }

    @Nullable
    @Override
    public V get(@NotNull K k) {
        int index = index(k);
        Cell<K, V> current = array[index];
        while (current != null && !current.key.equals(k)) {
            current = current.next;
        }
        return current == null? null: current.value;
    }

    @Override
    public void put(@NotNull K k, @NotNull V v) {
        int index = index(k);
        Cell<K, V> current = array[index];
        if (current == null) {
            array[index] = new Cell<>(k, v, null);
            size++;
        } else if (current.key.equals(k)) {
            current.value = v;
        } else {
            while (current.next != null && !current.next.key.equals(k)) {
                current = current.next;
            }
            if (current.next == null) {
                current.next = new Cell<>(k, v, null);
                size++;
            } else {
                current.next.value = v;
            }
        }
        if (size >= FILL_FACTOR * capacity) {
            resize();
        }
    }

    @Override
    public @Nullable V remove(@NotNull K k) {
        int index = index(k);
        Cell<K, V> current = array[index];
        if (current == null) {
            return null;
        }
        if (current.key.equals(k)) {
            V removeValue = current.value;
            array[index] = current.next;
            size--;
            return removeValue;
        }
        while (current.next != null && !current.next.key.equals(k)) {
            current = current.next;
        }
        if (current.next == null) {
            return null;
        }
        V removeValue = current.value;
        array[index] = current.next;
        size--;
        return removeValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

}
