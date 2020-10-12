package ru.mail.polis.ads.part3.vadim01er;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7497286

public class Task3 {

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

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        try (BufferedOutputStream out = new BufferedOutputStream(System.out)){
            HeapMin heapMin = new HeapMin(500_000);
            HeapMax heapMax = new HeapMax(500_000);
            int median;
            while (true) {
                int element = scanner.nextInt();
                if (heapMin.getSize() == 0 && heapMax.getSize() == 0) {
                    heapMax.insert(element); // left is main
                    byte[] buffer = (element + "\n").getBytes();
                    out.write(buffer, 0, buffer.length);
                    continue;
                }
                if (heapMin.getSize() == heapMax.getSize()) {
                    if (element > heapMin.min()) {
                        heapMax.insert(heapMin.extract());
                        heapMin.insert(element);
                    } else {
                        heapMax.insert(element);
                    }
                } else {
                    if (element > heapMax.max()) {
                        heapMin.insert(element);
                    } else {
                        heapMin.insert(heapMax.extract());
                        heapMax.insert(element);
                    }
                }
                if (heapMax.getSize() == heapMin.getSize()) {
                    median = (heapMax.max() + heapMin.min()) / 2;
                }
                else {
                    median = heapMax.max();
                }
                byte[] buffer = (median + "\n").getBytes();
                out.write(buffer, 0, buffer.length);
            }
        } catch (Exception ignored){ }
    }
}

class HeapMax {

    private int[] heap;
    private int size;

    public HeapMax(int n) {
        this.heap = new int[n + 1];
        this.size = 0;
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

    public int max(){
        return heap[1];
    }

    public void swim(int k) {
        while (k > 1 && heap[k] > heap[k / 2]) {
            int temp = heap[k];
            heap[k] = heap[k/2];
            heap[k/2] = temp;
            k = k/2;
        }
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

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Heap{" + Arrays.toString(heap) + "} " + size;
    }
}

class HeapMin {

    private int[] heap;
    private int size;

    public HeapMin(int n) {
        this.heap = new int[n + 1];
        this.size = 0;
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

    public int min() {
        return heap[1];
    }

    private void swim(int k) {
        while (k > 1 && heap[k] < heap[k / 2]) {
            int temp = heap[k];
            heap[k] = heap[k/2];
            heap[k/2] = temp;
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && heap[j] > heap[j + 1]) j++;
            if (heap[k] <= heap[j]) break;
            int temp = heap[k];
            heap[k] = heap[j];
            heap[j] = temp;
            k = j;
        }
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "Heap{" + Arrays.toString(heap) + "} " + size;
    }
}