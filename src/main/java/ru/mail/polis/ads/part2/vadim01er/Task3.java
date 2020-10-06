package ru.mail.polis.ads.part2.vadim01er;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.e-olymp.com/ru/submissions/7427993

public class Task3 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] arrInt = new int[n];
        int i = 0;
        for (String element : bufferedReader.readLine().split(" ")) arrInt[i++] = Integer.parseInt(element);
        System.out.println(sortBubble(arrInt, n));
    }

    public static int sortBubble(int[] array, int size) {
        int count = 0;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (array[j] > array[j+1]) {
                    array[j] += array[j+1];
                    array[j+1] = array[j] - array[j + 1];
                    array[j] = array[j] - array[j + 1];
                    count++;
                }
            }
        }
        return count;
    }
}