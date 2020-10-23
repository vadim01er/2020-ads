package ru.mail.polis.ads.vadim01er.part5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.e-olymp.com/ru/submissions/7569614

public class Task4 {

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        String first = scanner.next();
        String second;
        if (first.contains("*") || first.contains("?")) {
            second = first;
            first = scanner.next();
        } else second = scanner.next();
        boolean[][] d  = new boolean[second.length() + 1][first.length() + 1];
        loopEnd: for (int i = 1; i < second.length() + 1; i++) {
           for (int j = 1; j < first.length() + 1; j++) {
                if (i == 1 && j == 1) {
                    if (first.charAt(0) == second.charAt(0) || second.charAt(0) == '*' || second.charAt(0) == '?')
                        d[i][j] = true;
                    else break loopEnd;
                } else {
                    if (second.charAt(i-1) == first.charAt(j-1) || second.charAt(i-1) == '?')
                        d[i][j] = d[i-1][j-1];
                    else if (second.charAt(i-1) == '*')
                        d[i][j] = d[i-1][j-1] || d[i][j-1] || d[i-1][j];
                    else d[i][j] = false;
                }
            }
        }
        System.out.println(d[second.length()][first.length()] ? "YES": "NO");
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
