package ru.mail.polis.ads.part1.vadim01er;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

// https://www.e-olymp.com/ru/submissions/7413592

public class Task5 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Queue<Integer> queue = new Queue<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String answer = "";
            switch (line.split(" ")[0]) {
                case "push":
                    queue.push(Integer.parseInt(line.split(" ")[1]));
                    answer = "ok";
                    break;
                case "pop":
                    answer = String.valueOf(queue.pop());
                    break;
                case "front":
                    answer = String.valueOf(queue.front());
                    break;
                case "size":
                    answer = String.valueOf(queue.getSize());
                    break;
                case "clear":
                    queue.clear();
                    answer = "ok";
                    break;
                case "exit":
                    answer = "bye";
                    break;
            }
            System.out.println(answer);
            if (answer.equals("bye")) return;
        }
    }
}
class Queue<E> {
    private int size = 0;

    private Node<E> last = null;
    private Node<E> first = null;

    Queue() {
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;

        Node(Node<E>prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }
    }

    public void push(E element) {
        if (size == 0) {
            this.first = this.last = new Node<>(null, element, null);
        }
        else {
            final Node<E> last = this.last;
            final Node<E> newLast = new Node<>(null, element, last);
            this.last = newLast;
            last.prev = newLast;
        }
        size++;
    }

    public E pop() {
        if (size == 0) throw new NoSuchElementException();
        final Node<E> delete = this.first;
        this.first = delete.prev;
        size--;
        return delete.element;
    }

    public E front() {
        if (size == 0) throw new NoSuchElementException();
        return this.first.element;
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        Node<E> next;
        for (Node<E> a = this.last; a != null; a = next){
            next = a.next;
            a.element = null;
            a.next = null;
            a.prev = null;
        }
        this.size = 0;
        this.last = null;
        this.first = null;
    }
}
