package ru.mail.polis.ads.vadim01er.part9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

//

public class Task2 {

    private static class Graph {
        Map<Integer, List<Integer>> graph;

        public Graph(int n) {
            this.graph = new HashMap<>(n);
            for (int i = 1; i <= n; i++) {
                graph.put(i, new ArrayList<>());
            }
        }

        public void addEdge(Integer vertex1, Integer vertex2) {
            graph.get(vertex1).add(vertex2);
            graph.get(vertex2).add(vertex1);
        }

        public List<Integer> getEdges(Integer vertex) {
            if (!graph.containsKey(vertex))
                return Collections.emptyList();
            return graph.get(vertex);
        }

        @Override
        public String toString() {
            return "Graph{" +
                    "graph=" + graph +
                    '}';
        }
    }

    private static int start = -1;
    private static int end;
    private static Graph graph;

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        graph = new Graph(n);
        for (int i = 0; i < m; i++) {
            graph.addEdge(scanner.nextInt(), scanner.nextInt());
        }
        int[] color = new int[n + 1];
        int[] path = new int[n + 1];
        Arrays.fill(path, -1);

        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                if (hasCycle(i, color, path)) {
                    break;
                }
            }
        }

        if (start == -1) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
            int ans = start;
            for (int i = end; i != start; i = path[i]) {
                if (ans > i) {
                    ans = i;
                }
            }
            if (ans > start) {
                ans = start;
            }
            System.out.println(ans);
        }
    }

    private static boolean hasCycle( int vertex, int[] color, int[] path) {




        color[vertex] = 1;
        for (int vertex2 : graph.getEdges(vertex)) {
            if (color[vertex2] == 0) {
                path[vertex2] = vertex;
                if (hasCycle(vertex2, color, path)) {
                    return true;
                }
            } else if (color[vertex2] == 1 && vertex2 != path[vertex]) {
                end = vertex;
                start = vertex2;
                return true;
            }
        }
        color[vertex] = 2;
        return false;
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
