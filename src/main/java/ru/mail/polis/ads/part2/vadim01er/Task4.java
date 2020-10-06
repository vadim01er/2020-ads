package ru.mail.polis.ads.part2.vadim01er;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// https://www.e-olymp.com/ru/submissions/7446745

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = Integer.parseInt(scanner.nextLine());
        String[] arrString = scanner.nextLine().split(" ");
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(arrString));
        BigInteger[] bigIntegers = new BigInteger[arrayList.size()];
        for (int i = 0; i < bigIntegers.length; i++) {
            bigIntegers[i] = new BigInteger(arrayList.get(i));
        }
        int x = findK(bigIntegers, bigIntegers.length - k);
        System.out.println(bigIntegers[x]);
    }

    public static int findK(BigInteger[] arrInt, int k){
        int begin = 0, end = arrInt.length;
        while (true) {
            int p = lomuto(arrInt, begin, end);
            if (p == k) return p;
            if (k > p) {
                begin = p + 1;
            } else {
                end = p;
            }
        }
    }

    private static int lomuto(BigInteger[] bigIntegers, int l, int r) {
        BigInteger p = bigIntegers[l];
        int j = l;
        for (int i = l + 1; i < r; i++){
            if (bigIntegers[i].compareTo(p) < 0) {
                j++;
                BigInteger temp = bigIntegers[j];
                bigIntegers[j] = bigIntegers[i];
                bigIntegers[i] = temp;
            }
        }
        BigInteger temp = bigIntegers[j];
        bigIntegers[j] = bigIntegers[l];
        bigIntegers[l] = temp;
        return j;
    }
}