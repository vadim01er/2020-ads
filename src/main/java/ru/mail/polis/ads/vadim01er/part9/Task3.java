package ru.mail.polis.ads.vadim01er.part9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.e-olymp.com/ru/submissions/7879632

public class Task3 {

    private static class Edge {
        int first;
        int second;
        int weight;

        public Edge(int first, int second, int weight) {
            this.first = first;
            this.second = second;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "first=" + first +
                    ", second=" + second +
                    ", weight=" + weight +
                    '}';
        }
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            edges[i] = new Edge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }
        int[] d = new int[n+1];
        int INF = 30000;
        for (int i = 0; i < n+1; i++) {
            d[i] = INF;
        }
        d[1] = 0;
        for (int i = 1; i < n+1; i++) {
            for (int j = 0; j < m; j++) {
                if (d[edges[j].first] < INF) {
                    d[edges[j].second] = Math.min(d[edges[j].second], d[edges[j].first] + edges[j].weight);
                }
            }
        }
        for (int i = 1; i < n+1; i++) {
            System.out.print(d[i] + " ");
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
