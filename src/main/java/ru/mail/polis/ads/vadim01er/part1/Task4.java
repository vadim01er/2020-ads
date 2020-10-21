package ru.mail.polis.ads.vadim01er.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;

// https://www.e-olymp.com/ru/submissions/7413472

public class Task4 {
    public static void main(String[] args) throws IOException {
        Stack<Integer> stack = new Stack<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String answer = "";
            try {
                switch (line.split(" ")[0]) {
                    case "push":
                        stack.push(Integer.parseInt(line.split(" ")[1]));
                        answer = "ok";
                        break;
                    case "pop":
                        answer = String.valueOf(stack.pop());
                        break;
                    case "back":
                        answer = String.valueOf(stack.back());
                        break;
                    case "size":
                        answer = String.valueOf(stack.getSize());
                        break;
                    case "clear":
                        stack.clear();
                        answer = "ok";
                        break;
                    case "exit":
                        answer = "bye";
                        break;
                }
            } catch ( EmptyStackException e) {answer = "error";}
            System.out.println(answer);
            if (answer.equals("bye")) return;
        }
    }
}

class Stack<E> {
    private int size = 0;

    private Node<E> last = null;

    Stack() {
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    public void push(E element) {
        final Node<E> last = this.last;
        this.last = new Node<>(element, last);
        size++;
    }

    public E pop() {
        if (size == 0) throw new EmptyStackException();
        final Node<E> delete = this.last;
        this.last = this.last.next;
        size--;
        return delete.element;
    }

    public E back() {
        if (size == 0) throw new EmptyStackException();
        return this.last.element;
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
        }
        this.size = 0;
        this.last = null;
    }
}
