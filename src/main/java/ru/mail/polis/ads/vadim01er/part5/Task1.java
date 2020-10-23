package ru.mail.polis.ads.vadim01er.part5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

//https://www.e-olymp.com/ru/submissions/7562279

public class Task1 {

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        double in = Double.parseDouble(scanner.next());

        double m = 0, x = 0;
        double l = 0, r = in;
        DecimalFormat decimalFormat = new DecimalFormat("#.######");
        String decIn = decimalFormat.format(in);
        while (!decimalFormat.format(x).equals(decIn)) {
            m = (l + r) / 2;
            x = m * m + Math.sqrt(m);
            if (Double.compare(x, in) < 0) l = m;
            else r = m;
        }
        System.out.println(decimalFormat.format(m));
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
