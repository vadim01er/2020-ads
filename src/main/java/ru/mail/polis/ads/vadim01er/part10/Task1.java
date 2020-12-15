package ru.mail.polis.ads.vadim01er.part10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.e-olymp.com/ru/submissions/8019440

public class Task1 {

    private static class Graph {

        private static class Pair<Integer> {
            private final int vertex;
            private final int number;

            public Pair(int vertex, int number) {
                this.vertex = vertex;
                this.number = number;
            }

            public int getVertex() {
                return vertex;
            }

            public int getNumber() {
                return number;
            }
        }

        List<List<Pair<Integer>>> graph;

        public Graph(int n) {
            this.graph = new ArrayList<>(n);
            for (int i = 1; i <= n; i++) {
                graph.add(new ArrayList<>());
            }
        }

        public void addEdge(Integer vertex1, Integer vertex2, int i) {
            graph.get(vertex1).add(new Pair<>(vertex2, i));
            graph.get(vertex2).add(new Pair<>(vertex1, i));
        }

        public List<Pair<Integer>> getEdges(Integer vertex) {
            return graph.get(vertex);
        }

        @Override
        public String toString() {
            return "Graph{" +
                    "graph=" + graph +
                    '}';
        }
    }

    static Graph graph;
    static boolean[] visited;
    static int[] tin;
    static int[] fup;
    static int timer = 0;
    static SortedSet<Integer> answer = new TreeSet<>();

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        graph = new Graph(n);
        for (int i = 1; i <= m; i++) {
            graph.addEdge(scanner.nextInt() - 1, scanner.nextInt() - 1, i);
        }
        visited = new boolean[n + 1];
        tin = new int[n + 1];
        fup = new int[n + 1];
        timer = 0;

        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                dfs(i, -1);
            }
        }
        System.out.println(answer.size());
        for (int i : answer) {
            System.out.print(i + " ");
        }
    }

    private static void dfs(int v, int p) {
        visited[v] = true;
        tin[v] = fup[v] = timer++;
        for (Graph.Pair<Integer> to : graph.getEdges(v)) {
            if (to.getVertex() == p) {
                continue;
            }
            if (visited[to.getVertex()]) {
                fup[v] = Math.min(fup[v], tin[to.getVertex()]);
            } else {
                dfs(to.getVertex(), v);
                fup[v] = Math.min(fup[v], fup[to.getVertex()]);
                if (fup[to.getVertex()] > tin[v]) {
                    answer.add(to.getNumber());
                }
            }
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
