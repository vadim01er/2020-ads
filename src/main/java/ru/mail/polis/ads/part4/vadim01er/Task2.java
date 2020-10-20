package ru.mail.polis.ads.part4.vadim01er;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7523832

public class Task2 {

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) matrix[i][j] = scanner.nextInt();
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (i == m - 1 && j == 0) {
                    matrix[i][j] = matrix[i][j];
                } else if (i == m - 1) {
                    matrix[i][j] = matrix[i][j] + matrix[i][j - 1];
                } else if (j == 0) {
                    matrix[i][0] = matrix[i][0] + matrix[i + 1][0];
                } else matrix[i][j] = matrix[i][j] + Math.max(matrix[i + 1][j], matrix[i][j - 1]);

            }
        }

        StringBuilder out = new StringBuilder();
        int i = 0;
        int j = n - 1;
        while (i < m - 1 || j > 0) {
            if (i < m - 1 && j > 0) {
                if (matrix[i + 1][j] > matrix[i][j - 1]) {
                    out.append("F");
                    i++;
                } else {
                    out.append("R");
                    j--;
                }
            } else if (i < m - 1) {
                out.append("F");
                i++;
            } else {
                out.append("R");
                j--;
            }
        }
        System.out.println(out.reverse());
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
