package day5;

import java.util.Scanner;

public class PracticeEleven {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Please enter the mass of an object: ");
        double mass = in.nextDouble();

        System.out.print("Please enter the velocity of an object: ");
        double velocity = in.nextDouble();

        double kineticEnergy = (0.5) * mass * Math.pow(velocity,2);
        System.out.println("This object's kinetic energy is " + kineticEnergy);
        
        in.close();
    }
}
