package day5;

import java.util.Scanner;

public class PracticeEight {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Please enter a positive number: ");
        double number = in.nextDouble();

        System.out.println("The square of this number is " + Math.pow(number,2) + " and the square root of this number is " + Math.sqrt(number) + ".");

        in.close();
    }
}
