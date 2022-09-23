package day5;

public class PracticeTwo {
    public static void main(String[] args) {
        double length = 4.5, width = 2.3;
        
        double perimeter = Math.round((2 * length + 2 * width) * 10) / 10.0;
        double area = Math.round((length * width) * 10) / 10.0;

        System.out.println("The perimeter is " + perimeter + "ft, and the area is " + area + "ft squared.");
    }
}
