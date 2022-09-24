package day5;

import java.util.Scanner;

public class PracticeTen {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Please enter the length of a rectangle: ");
        double length = in.nextDouble();

        System.out.println("Please enter the width of a rectangle: ");
        double width = in.nextDouble();

        double perimeter = (2*length) + (2*width), area = length*width;
        System.out.println("The perimeter of this rectangle is " + perimeter + " and the area of this rectangle is " + area);

        in.close();
    }
}
