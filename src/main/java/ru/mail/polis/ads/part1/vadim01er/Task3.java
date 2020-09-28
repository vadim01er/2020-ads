package ru.mail.polis.ads.part1.vadim01er;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.e-olymp.com/ru/submissions/7377573

public class Task3 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = bufferedReader.readLine();
        int count = 0;
        for (char i: line.toCharArray()) {
            if (i == '(') count++;
            else count--;
            if (count < 0) break;
        }
        System.out.println(count == 0? "YES" : "NO");
    }
}
