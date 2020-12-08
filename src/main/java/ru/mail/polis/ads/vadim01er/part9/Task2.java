package ru.mail.polis.ads.vadim01er.part9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.e-olymp.com/ru/submissions/7985449

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
        boolean[] cycle = new boolean[n + 1];
        Arrays.fill(path, -1);

        for (int i = 1; i <= n; i++) {
            if (color[i] == 0) {
                findCycle(i, color, path, cycle);
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i < cycle.length; i++) {
            if (cycle[i] && min > i) {
                min = i;
            }
        }

        if (min == Integer.MAX_VALUE) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
            System.out.println(min);
        }
    }

    private static void findCycle( int vertex, int[] color, int[] path, boolean[] cycle) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.addFirst(vertex);
        while (!queue.isEmpty()) {
            int current = queue.peek();
            color[current] = 1;
            boolean isCycle = false;
            for (int next : graph.getEdges(current)) {
                if (color[next] == 0 && !(path[current] == next)) {
                    path[next] = current;
                    queue.push(next);
                    isCycle = true;
                    break;
                } else if (color[next] == 1 && !(path[current] == next)) {
                    int j = current;
                    cycle[next] = true;
                    if (cycle[j]) {
                        continue;
                    }
                    while (j != next) {
                        cycle[j] = true;
                        j = path[j];
                        if (cycle[j]) {
                            break;
                        }
                    }
                }
            }
            if (!isCycle) {
                queue.pop();
                color[current] = 2;
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
