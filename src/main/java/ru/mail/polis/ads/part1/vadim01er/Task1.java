package ru.mail.polis.ads.part1.vadim01er;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//https://www.e-olymp.com/ru/submissions/7373551

public class Task1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(bufferedReader.readLine());
        System.out.println(number / 10 + " " + number % 10);
    }
}