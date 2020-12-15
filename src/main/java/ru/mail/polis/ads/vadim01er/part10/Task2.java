package ru.mail.polis.ads.vadim01er.part10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.e-olymp.com/ru/submissions/8019869

public class Task2 {

    private static class Edge {
        int vertex1, vertex2, weight;

        public Edge(int vertex1, int vertex2, int weight) {
            this.vertex1 = vertex1;
            this.vertex2 = vertex2;
            this.weight = weight;
        }
    }

    static int[] p;
    static int[] rk;

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            edges.add(new Edge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        }
        p = new int[n+1];
        rk = new int[n+1];
        for (int i = 1; i <= n; i++) {
            p[i] = i;
            rk[i] = 1;
        }
        edges.sort(Comparator.comparingInt(o -> o.weight));
        int minWeight = 0;
        for (Edge edge : edges) {
            if (merge(edge.vertex1, edge.vertex2)) {
                minWeight += edge.weight;
            }
        }
        System.out.println(minWeight);
    }

    private static boolean merge(int vertex1, int vertex2) {
        int ra = getRoot(vertex1);
        int rb = getRoot(vertex2);
        if (ra == rb) {
            return false;
        } else {
            if (rk[ra] < rk[rb]) {
                p[ra] = rb;
            } else if (rk[rb] < rk[ra]) {
                p[rb] = ra;
            } else {
                p[ra] = rb;
                rk[rb]++;
            }
            return true;
        }
    }

    static int getRoot(int v) {
        if (p[v] == v) {
            return v;
        } else {
            return p[v] = getRoot(p[v]);
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
