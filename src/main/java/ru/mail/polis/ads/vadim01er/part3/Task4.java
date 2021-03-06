package ru.mail.polis.ads.vadim01er.part3;

import java.io.*;
import java.util.StringTokenizer;

public class Task4 {

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
        try (PrintWriter out = new PrintWriter(System.out)) {
            int n = scanner.nextInt();
            int q = scanner.nextInt();
            int[] arrInt = new int[n];
            for (int i = 0; i < n; i++) {
                arrInt[i] = scanner.nextInt();
            }
            stop:
            while (q > 0) {
                q--;
                int checkValue = scanner.nextInt();
                int begin = 0;
                int end = arrInt.length;
                int mid = (begin + end) / 2;
                while (begin < end) {
                    if (arrInt[mid] == checkValue) {
                        out.write("YES\n");
                        continue stop;
                    }
                    if (arrInt[mid] > checkValue) {
                        end = mid;
                    } else begin = mid + 1;
                    mid = (begin + end) / 2;
                }
                out.write("NO\n");

            }
        }
    }
}