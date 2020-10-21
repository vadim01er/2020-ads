package ru.mail.polis.ads.vadim01er.part3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7474514

public class Task2 {

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = Integer.parseInt(scanner.next());
        Heap heap = new Heap(n);
        for (int i = 0; i < n; i ++) {
            switch (scanner.next()) {
                case "0":
                    heap.insert(scanner.nextInt());
                    break;
                case "1":
                    System.out.println(heap.extract());
                    break;
                case "2":
                    System.out.println(heap);
            }
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    private static class Heap {

        private int[] heap;
        private int size;

        public Heap(int n) {
            this.heap = new int[n + 1];
            this.size = 0;
        }

        public void swim(int k) {
            while (k > 1 && heap[k] > heap[k / 2]) {
                int temp = heap[k];
                heap[k] = heap[k/2];
                heap[k/2] = temp;
                k = k/2;
            }
        }

        public void insert(int element) {
            heap[++size] = element;
            swim(size);
        }

        public int extract() {
            int out = heap[1];
            heap[1] = heap[size--];
            sink(1);
            return out;
        }

        public void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && heap[j] < heap[j + 1]) j++;
                if (heap[k] >= heap[j]) break;
                int temp = heap[k];
                heap[k] = heap[j];
                heap[j] = temp;
                k = j;
            }
        }

        @Override
        public String toString() {
            return "Heap{" + Arrays.toString(heap) + "} " + size;
        }
    }
}
