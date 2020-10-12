package ru.mail.polis.ads.part3.vadim01er;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7499447

public class Task5 {

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
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] coordinates = new int[n];
        for (int i = 0; i < n; i++) coordinates[i] = scanner.nextInt();
        int left = 0;
        int right = coordinates[coordinates.length - 1] - coordinates[0] + 1;
        while (right - left > 1) {
            int mid = (left + right) / 2;
            if (check(mid, coordinates) >= k) left = mid;
            else right = mid;
        }
        System.out.println(left);
    }

    private static int check(int mid, int[] coordinates) {
        int count = 1;
        int lastCow = coordinates[0];
        for (int coor: coordinates) {
            if (coor - lastCow >= mid) {
                count++;
                lastCow = coor;
            }
        }
        return count;
    }
}
