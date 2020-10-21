package ru.mail.polis.ads.vadim01er.part2;

import java.util.Arrays;
import java.util.Scanner;

// https://www.e-olymp.com/ru/submissions/7440826

public class Task5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Pair<Integer, Integer>[] arrPair = new Pair[n];
        for (int i = 0; i < n; i++) {
            String[] line = scanner.nextLine().split(" ");
            arrPair[i] = new Pair<>(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
        }
        mergeSort(arrPair, 0, n);
        for (Pair<Integer, Integer> element : arrPair) {
            System.out.println(element);
        }
    }

    private static class Pair<K, V> {
        K first;
        V second;

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return first + " " + second;
        }
    }

    private static void mergeSort(Pair<Integer, Integer>[] elements, int begin, int end) {
        if (end - begin <= 1) return;
        int middle = (begin + end) / 2;
        mergeSort(elements, begin, middle);
        mergeSort(elements, middle, end);
        merge(elements, begin, middle, end);
    }

    private static void merge(Pair<Integer, Integer>[] elements, int begin, int middle, int end) {
        Pair<Integer, Integer>[] left = Arrays.copyOfRange(elements, begin, middle);
        Pair<Integer, Integer>[] right = Arrays.copyOfRange(elements, middle, end);
        int li = 0, ri = 0;
        for (int i = begin; i < end; i++) {
            if (li < left.length && (ri == right.length || left[li].first <= right[ri].first)) {
                elements[i] = left[li++];
            } else {
                elements[i] = right[ri++];
            }
        }
    }

}
