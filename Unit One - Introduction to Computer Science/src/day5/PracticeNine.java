package day5;

import java.util.Scanner;

public class PracticeNine {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the number of items sold: ");
        int itemsSold = in.nextInt();

        System.out.println("You have $" + itemsSold * 0.27 + " due on your account.");

        in.close();
    }
}
