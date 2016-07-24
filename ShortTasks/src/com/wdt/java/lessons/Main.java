package com.wdt.java.lessons;

import java.io.*;
import java.util.NavigableSet;
import java.util.Scanner;
import java.util.TreeSet;


public class Main {

    static NavigableSet<Double> navigableSet = new TreeSet<Double>();


    public static void read(String fileName) throws FileNotFoundException {

        File file = new File(fileName);

        file.exists();

        try {
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    if (!navigableSet.add(Double.parseDouble(s))) {
                        System.out.println("Something goes WRONG!!!");
                    }
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
	// write your code here

        try {
            read("int_nums.txt");
            System.out.println(navigableSet.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Введите дробное число от 0 до 100");

        Scanner dbl = new Scanner(System.in);
        Double  i = dbl.nextDouble();

        System.out.println("Ближайшее наименьшее - "+navigableSet.floor(i));
        System.out.println("Ближайшее наибольшее - "+navigableSet.ceiling(i));

    }
}
