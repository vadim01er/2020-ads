package ru.mail.polis.ads.part3.vadim01er;

import java.util.Scanner;

//  https://www.e-olymp.com/ru/submissions/7468896

public class Task1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        int[] arrHeap = new int[++n];
        for (int i = 1; i < n; i++) arrHeap[i] = scanner.nextInt();
        for (int i = 1; 2 * i < n; i++) {
            if (arrHeap[i] > arrHeap[2 * i] || (2 * i + 1 > n && arrHeap[i] > arrHeap[2 * i + 1] )) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
}