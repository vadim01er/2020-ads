package ru.mail.polis.ads.part2.vadim01er;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7436333

public class Task2 {

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        int n = scanner.nextInt();
        Integer[] arrInt = new Integer[n];
        for (int i = 0; i < n; i++) arrInt[i] = scanner.nextInt();
        Arrays.sort(arrInt, (i1, i2) -> {
            int modI = i1 % 10;
            int modJ = i2 % 10;
            if (modI == modJ) {
                return Integer.compare(i1, i2);
            } else return Integer.compare(modI, modJ);
        });
        for (int i = 0; i < n; i++) { System.out.print(arrInt[i] + " "); }
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
