package ru.mail.polis.ads.vadim01er.part9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.e-olymp.com/ru/submissions/7978947

public class Task4 {

    private static class Graph {
        Map<Integer, Set<ToVertex>> graph;

        private static class ToVertex {
            private final int vertex;
            private final int weight;

            public ToVertex(int vertex, int weight) {
                this.vertex = vertex;
                this.weight = weight;
            }
        }

        public Graph(int n) {
            this.graph = new HashMap<>();
            for (int i = 1; i <= n; i++) {
                graph.put(i, new HashSet<>());
            }
        }

        public void addEdge(int vertex1, int vertex2, int weight) {
            graph.get(vertex1).add(new ToVertex(vertex2, weight));
            graph.get(vertex2).add(new ToVertex(vertex1, weight));
        }

        public Set<ToVertex> getEdges(Integer vertex) {
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
            graph.addEdge(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
        }
        int[] length = new int[n + 1];
        int[] path = new int[n + 1];

        Arrays.fill(length, 200000);
        length[a] = 0;
        path[a] = -1;

        PriorityQueue<Graph.ToVertex> queue =
                new PriorityQueue<>(
                        Comparator.comparing(integerVertex -> length[integerVertex.vertex])
                );
        queue.add(new Graph.ToVertex(a, 0));
        while (!queue.isEmpty()) {
            Graph.ToVertex toVertex = queue.poll();
            if (toVertex.vertex == b) {
                break;
            }
            for (Graph.ToVertex i: graph.getEdges(toVertex.vertex)) {
                if (length[i.vertex] > length[toVertex.vertex] + i.weight) {
                    length[i.vertex] = length[toVertex.vertex] + i.weight;
                    path[i.vertex] = toVertex.vertex;
                    queue.add(i);
                }
            }
        }

        if (path[b] == -1) {
            System.out.println("-1");
            return;
        }
        System.out.println(length[b]);
        List<Integer> out = new ArrayList<>();
        for (int vertex = b; vertex!= -1; vertex = path[vertex]) {
            out.add(vertex);
        }
        for (int i = out.size(); i > 0; i--) {
            System.out.print(out.get(i - 1) + " ");
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
