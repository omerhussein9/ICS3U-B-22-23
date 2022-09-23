package day5;

import java.util.Scanner;

public class ExampleTwo {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter a decimal number: ");
        double number = in.nextDouble();

        number = Math.round(number * 100) / 100.0;
        System.out.println(number);

        System.out.println("Please enter an integer: ");
        int iNumber = in.nextInt();
        iNumber = (int) Math.round(iNumber / 100.0) * 100;
        System.out.println("Rounded: " + iNumber);

        in.close();
    }
}
