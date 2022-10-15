package BayviewGlenPoolAssignment;

import java.util.Scanner;

public class Pool {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //initializing all of the needed variables

        // System.out.println("Please enter the length of the pool in metres: ");
        // double length = in.nextDouble();

        // System.out.println("Please enter the length of the shallow end in metres: ");
        // double shallowLength = in.nextDouble();

        // System.out.println("Please enter the length of the transition area in metres: ");
        // double transition = in.nextDouble();

        // System.out.println("Please enter the depth of the shallow end in metres: ");
        // double shallowHeight = in.nextDouble();

        // System.out.println("Please enter the depth of the deep end in metres: ");
        // double deepHeight = in.nextDouble();

        // System.out.println("Please enter the width of the pool in metres: ");
        // double width = in.nextDouble();

        // System.out.println("Please enter the price of the liner per metre squared: ");
        // double linerCost = in.nextDouble();

        double length = 20;
        double shallowLength = 5;
        double transition = 7;
        double shallowHeight = 3;
        double deepHeight = 8;
        double width = 8;

        double linerCost = 2;

        //Step 1 - Calculating the volume of the pool

        double transitionLength = Math.sqrt(Math.pow(transition,2) - Math.pow(deepHeight - shallowHeight,2));

        double volume1 = length * width * shallowHeight;
        double volume2 = (length - shallowLength - transitionLength) * width * (deepHeight - shallowHeight);
        double volume3 = (transitionLength * width * (deepHeight - shallowHeight))/2;

        double poolVolume = volume1 + volume2 + volume3;

        System.out.println("You will need " + poolVolume * 0.9 + " cubic metres of water to fill this pool.");

        //Step 2 - Calculating the amount of liner needed to create the pool (surface area)

        double surfaceArea1 = (length * shallowHeight) * 2;
        double surfaceArea2 = ((length - shallowLength - transitionLength) * (deepHeight - shallowHeight)) * 2;
        double surfaceArea3 = transitionLength * (deepHeight - shallowHeight);
        double surfaceArea4 = width * deepHeight;
        double surfaceArea5 = width * shallowHeight;
        double surfaceArea6 = (length - shallowLength - transitionLength) * width;
        double surfaceArea7 = shallowLength * width;
        double surfaceArea8 = transition * width;

        double poolSurfaceArea = surfaceArea1 + surfaceArea2 + surfaceArea3 + surfaceArea4 + surfaceArea5 + surfaceArea6 + surfaceArea7 + surfaceArea8;
        
        System.out.println("You will need " + poolSurfaceArea + " metres squared of liner to cover this pool.");

        //Step 3 - Calculating the cost of the liner

        double rawPrice = poolSurfaceArea * linerCost;
        String roundedPrice = String.valueOf(rawPrice).substring(0,7);

        System.out.println("To cover this pool's surface area, $" + roundedPrice + " of liner is needed.");
        
        in.close();
    }
}