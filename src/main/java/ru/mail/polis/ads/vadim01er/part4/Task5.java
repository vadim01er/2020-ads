package ru.mail.polis.ads.vadim01er.part4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7535696

public class Task5 {

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) arr[i] = scanner.nextInt();
        System.out.println(mergeSort(arr, 0, arr.length));
    }

    private static int mergeSort(Integer[] elements, int begin, int end) {
        if (end - begin <= 1) return 0;
        int middle = (begin + end) / 2;
        int inv = 0;
        inv += mergeSort(elements, begin, middle);
        inv += mergeSort(elements, middle, end);
        inv += merge(elements, begin, middle, end);
        return inv;
    }

    private static int merge(Integer[] elements, int begin, int middle, int end) {
        Integer[] left = Arrays.copyOfRange(elements, begin, middle);
        Integer[] right = Arrays.copyOfRange(elements, middle, end);
        int li = 0, ri = 0, inv = 0;
        for (int i = begin; i < end; i++) {
            if (li < left.length && (ri == right.length || left[li] <= right[ri])) {
                elements[i] = left[li++];
            } else {
                elements[i] = right[ri++];
                inv += left.length - li;
            }
        }
        return inv;
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
}
