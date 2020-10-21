package ru.mail.polis.ads.vadim01er.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.e-olymp.com/ru/submissions/7427037

public class Task1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(bufferedReader.readLine());
        int[] arrInt = new int[size];
        int i = 0;
        for (String element: bufferedReader.readLine().split(" ")) {
            arrInt[i++] = Integer.parseInt(element);
        }
        mergeSort(arrInt, 0 , size);
        boolean isFirst = false;
        for (int j = 0; j < size; j++) {
            if (isFirst) System.out.print(" ");
            System.out.print(arrInt[j]);
            isFirst = true;
        }
    }

    private static void mergeSort(int[] elements, int begin, int end){
        if (end - begin <= 1) return;
        int middle = (begin + end) / 2;
        mergeSort(elements, begin, middle);
        mergeSort(elements, middle, end);
        merge(elements, begin, middle, end);
    }
    private static void merge(int[] elements, int begin, int middle, int end){
        int[] left = Arrays.copyOfRange(elements, begin, middle);
        int[] right = Arrays.copyOfRange(elements, middle , end);
        int li = 0, ri = 0;
        for (int i = begin; i < end; i++) {
            if (li < left.length && (ri == right.length || left[li] <= right[ri])) {
                elements[i] = left[li++];
            } else {
                elements[i] = right[ri++];
            }
        }
    }
}
