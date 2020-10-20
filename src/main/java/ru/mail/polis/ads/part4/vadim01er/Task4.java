package ru.mail.polis.ads.part4.vadim01er;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7526272

public class Task4 {

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[][] arr = new int[2][n + 2];
        for (int i = 1; i < n + 1; i++) arr[0][i] = scanner.nextInt();
        int k = scanner.nextInt();
        for (int i = 1; i < n + 2; i++) {
            arr[1][i] = arr[1][i - 1];
            for (int j = Math.max(i - k, 0); j < i; j++) {
                arr[1][i] = Math.max(arr[1][i], arr[1][j]);
            }
            arr[1][i] += arr[0][i];
        }
        System.out.println(arr[1][n + 1]);
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
