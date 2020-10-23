package ru.mail.polis.ads.vadim01er.part5;

import java.io.*;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7567175

public class Task2 {

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        long w = scanner.nextInt();
        long h = scanner.nextInt();
        long n = scanner.nextInt();
        long l = Math.max(w, h);
        long r = Math.max(w, h) * n;
        while (l < r) {
            long m = (l + r) / 2;
            long count = (m / w) * (m / h);
            if (count >= n) r = m;
            else l = m + 1;
        }
        System.out.println(l);
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
