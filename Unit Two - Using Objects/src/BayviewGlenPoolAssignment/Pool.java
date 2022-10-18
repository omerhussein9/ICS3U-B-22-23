package BayviewGlenPoolAssignment;

import java.util.Scanner;

public class Pool {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //initializing all of the needed variables

        System.out.println("Please enter the length of the pool in metres: ");
        double length = in.nextDouble();

        System.out.println("Please enter the length of the shallow end in metres: ");
        double shallowLength = in.nextDouble();

        System.out.println("Please enter the length of the transition area in metres: ");
        double transition = in.nextDouble();

        System.out.println("Please enter the depth of the shallow end in metres: ");
        double shallowHeight = in.nextDouble();

        System.out.println("Please enter the depth of the deep end in metres: ");
        double deepHeight = in.nextDouble();

        System.out.println("Please enter the width of the pool in metres: ");
        double width = in.nextDouble();

        System.out.println("Please enter the price of the liner per metre squared: ");
        double linerCost = in.nextDouble();

        //Step 1 - Calculating the volume of the pool

        double transitionLength = Math.sqrt(Math.pow(transition,2) - Math.pow(deepHeight - shallowHeight,2));

        double volume1 = length * width * shallowHeight;
        double volume2 = (length - shallowLength - transitionLength) * width * (deepHeight - shallowHeight);
        double volume3 = (transitionLength * width * (deepHeight - shallowHeight))/2;

        double poolVolume = volume1 + volume2 + volume3;

        System.out.println("You will need " + poolVolume * 0.9 + " cubic metres of water to fill this pool.");

        //Step 2 - Calculating the amount of liner needed to create the pool (surface area)

        double poolSurfaceArea = (2 * ((length * shallowHeight) + (length - shallowLength - transitionLength) * (deepHeight - shallowHeight))) +
        (transitionLength * (deepHeight - shallowHeight)) + (width * deepHeight) + (width * shallowHeight) + ((length - shallowLength - transitionLength) * width) +
        (shallowLength * width) + (transition * width);
        
        System.out.println("You will need " + poolSurfaceArea + " metres squared of liner to cover this pool.");

        //Step 3 - Calculating the cost of the liner

        String rawPrice = String.valueOf(poolSurfaceArea * linerCost);
        String roundedPrice = rawPrice.substring(0,rawPrice.indexOf('.') + 3);

        System.out.println("To cover this pool's surface area, $" + roundedPrice + " of liner is needed.");
        
        in.close();
    }
}
