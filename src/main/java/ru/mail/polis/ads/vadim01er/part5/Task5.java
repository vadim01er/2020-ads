package ru.mail.polis.ads.vadim01er.part5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7558197

public class Task5 {

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        int[] help = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
            help[i] = n - i;
            System.out.print(i + 1 + " ");
        }
        System.out.print("\n");

        while (!Arrays.equals(arr, help)) {
            int i = n - 1;
            while (i != 0 && arr[i-1] > arr[i]) i--;

            int before = i - 1;
            int after = n - 1;
            while (arr[after] < arr[before]) after--;

            int temp = arr[after];
            arr[after] = arr[before];
            arr[before] = temp;

            int[] copy = Arrays.copyOfRange(arr, i, n);
            int j = copy.length - 1;
            for (int k = i; k < n; k++) arr[k] = copy[j--];

            for (int el : arr) System.out.print(el + " ");
            System.out.print("\n");
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
}
