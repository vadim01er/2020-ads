package ru.mail.polis.ads.part4.vadim01er;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

// https://www.e-olymp.com/ru/submissions/7546917

public class Task1 {

    public static void main(String[] args) throws IOException {
        FastScanner scanner = new FastScanner(System.in);
        char[] line = scanner.reader.readLine().toCharArray();
        if (line.length == 0)
            return;
        int[][] matrix = new int[line.length][line.length];
        char[][][] str = new char[line.length][line.length][];
        for (int i = 0; i < line.length; i++) {
            matrix[i][i] = 1;
            str[i][i] = getCorrect(line[i]);
        }
        for (int i = 0; i < line.length; i++) {
            for (int j = i; j < line.length; j++) {
                int newI = j - i;
                int newJ = j;
                if (newI == newJ) {
                    matrix[newI][newI] = 1;
                    str[newI][newI] = getCorrect(line[newI]);
                } else {
                    int min = matrix[newI][newI] + matrix[newI + 1][newJ];
                    int minK = newI;
                    for (int k = newI + 1; k < newJ; k++) {
                        int temp = matrix[newI][k] + matrix[k + 1][newJ];
                        if (min > temp) {
                            min = temp;
                            minK = k;
                        }
                    }
                    if ((line[newI] == '(' && line[newJ] == ')' || line[newI] == '[' && line[newJ] == ']')
                            && min > matrix[newI + 1][newJ - 1]) {
                        matrix[newI][newJ] = matrix[newI + 1][newJ - 1];
                        if (str[newI + 1][newJ - 1] == null) str[newI][newJ] = new char[]{line[newI], line[newJ]};
                        else {
                            char[] temp = mergeArrays(new char[]{line[newI]}, str[newI + 1][newJ - 1]);
                            str[newI][newJ] = mergeArrays(temp, new char[]{line[newJ]});
                        }
                    } else {
                        matrix[newI][newJ] = min;
                        str[newI][newJ] = mergeArrays(str[newI][minK], str[minK + 1][newJ]);
                    }
                }

            }
        }
        System.out.println(str[0][line.length - 1]);
    }

    private static char[] getCorrect(char c) {
        if (c == '(' || c == ')') {
            return new char[]{'(', ')'};
        } else {
            return new char[]{'[', ']'};
        }
    }

    private static char[] mergeArrays(char[] left, char[] right) {
        char[] res = new char[left.length + right.length];
        System.arraycopy(left, 0, res, 0, left.length);
        System.arraycopy(right, 0, res, left.length, right.length);
        return res;
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
