package ru.mail.polis.ads.vadim01er.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

// https://www.e-olymp.com/ru/submissions/7413219

public class Task2 {
    static class NodeTree<E>{
        E element;
        NodeTree<E> left;
        NodeTree<E> right;

        NodeTree(E element){
            this.element = element;
            this.left = null;
            this.right = null;
        }

        NodeTree(E element, NodeTree<E> left, NodeTree<E> right){
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < n; i++) {
            String inputLine = bufferedReader.readLine();
            Stack<NodeTree<Character>> stack = new Stack<>();

            // используя стек делам дерево
            for (char ch : inputLine.toCharArray()) {
                if (Character.isLowerCase(ch)) {
                    stack.push(new NodeTree<>(ch));
                } else {
                    NodeTree<Character> right = stack.pop();
                    NodeTree<Character> left = stack.pop();
                    stack.push(new NodeTree<>(ch, left, right));
                }
            }
            StringBuilder output = new StringBuilder();
            Queue<NodeTree<Character>> queue = new Queue<>();
            queue.push(stack.pop());

            // используя очередь преобразуем дерево в строку
            while (queue.size != 0) {
                NodeTree<Character> node = queue.pop();
                output.insert(0, node.element);
                if (node.left != null) queue.push(node.left);
                if (node.right != null) queue.push(node.right);
            }
            System.out.println(output.toString());
        }
    }

    static class Stack<E> {
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
    }

    static class Queue<E> {
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
            this.first = this.first.prev;
            size--;
            return delete.element;
        }
    }
}
