package ru.mail.polis.ads.vadim01er.part9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.e-olymp.com/ru/submissions/7978103

public class Task5 {

    private static class Graph {
        Map<Integer, Set<Integer>> graph;

        public Graph(int n) {
            this.graph = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                graph.put(i, new HashSet<>());
            }
        }

        public void addEdge(Integer vertex1, Integer vertex2) {
            graph.get(vertex1).add(vertex2);
            graph.get(vertex2).add(vertex1);
        }

        public Set<Integer> getEdges(Integer vertex) {
            return graph.get(vertex);
        }

        @Override
        public String toString() {
            return "Graph{" +
                    "graph=" + graph +
                    '}';
        }
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        Graph graph = new Graph(n);
        for (int i = 0; i < m; i++) {
            graph.addEdge(scanner.nextInt(), scanner.nextInt());
        }
        int[] path = new int[n + 1];
        boolean[] visited = new boolean[n+1];
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(a);
        visited[a] = true;
        path[a] = -1;
        loop: while (!queue.isEmpty()) {
            int current = queue.pop();
            for (int vertex: graph.getEdges(current)) {
                if (!visited[vertex]) {
                    visited[vertex] = true;
                    queue.add(vertex);
                    path[vertex] = current;
                }
                if (vertex == b)
                    break loop;
            }
        }

        if (!visited[b]){
            System.out.println("-1");
        } else {
            List<Integer> out = new ArrayList<>();
            for (int vertex = b; vertex!= -1; vertex = path[vertex]) {
                out.add(vertex);
            }
            System.out.println(out.size() - 1);
            for (int i = out.size(); i > 0; i--) {
                System.out.print(out.get(i - 1) + " ");
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
