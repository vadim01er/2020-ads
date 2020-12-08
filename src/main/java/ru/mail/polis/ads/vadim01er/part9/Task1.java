package ru.mail.polis.ads.vadim01er.part9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.e-olymp.com/ru/submissions/7977034

public class Task1 {

    private static class Graph {
        Map<Integer, List<Integer>> graph;
        private final int size;

        public Graph(int n) {
            this.graph = new HashMap<>();
            this.size = n;
            for (int i = 1; i <= n; i++) {
                graph.put(i, new ArrayList<>());
            }
        }
        public void addEdge(Integer vertex1, Integer vertex2) {
            graph.get(vertex1).add(vertex2);
        }

        private void utilForTopologicalSort(int v, boolean[] visited, Stack<Integer> stack) {
            visited[v] = true;

            for (int i : graph.get(v)) {
                if (!visited[i])
                    utilForTopologicalSort(i, visited, stack);
            }

            stack.push(v);
        }

        void topologicalSort() {
            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[size + 1];
            Arrays.fill(visited, false);

            for (int i = 1; i <= size; i++) {
                if (!visited[i]) {
                    utilForTopologicalSort(i, visited, stack);
                }
            }

            while (!stack.empty()) {
                System.out.print(stack.pop() + " ");
            }
        }

        boolean haveCycle() {
            int[] color = new int[size + 1];

            for (int i = 1; i <= size; i++) {
                if (color[i] == 0) {
                    if (dfs(i, color)) {
                        return true;
                    }
                }
            }
            return false;
        }

        boolean dfs(int vertex, int[] color) {
            color[vertex] = 1;
            for (int i : graph.get(vertex)) {
                if (color[i] == 0) {
                    if (dfs(i, color)) {
                        return true;
                    }
                } else if (color[i] == 1) {
                    return true;
                }
            }
            color[vertex] = 2;
            return false;
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
        Graph graph = new Graph(n);
        for (int i = 0; i < m; i++) {
            graph.addEdge(scanner.nextInt(), scanner.nextInt());
        }
        if (graph.haveCycle()){
            System.out.print("-1");
            return;
        }
        graph.topologicalSort();

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
