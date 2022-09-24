package day5;

public class PracticeFive {
    public static void main(String[] args) {
        int numberOfWins = 110;
        int numberOfLosses = 44;

        double winningPercentage = (double) numberOfWins/(numberOfWins+numberOfLosses);
        winningPercentage = Math.round(winningPercentage * 1000) / 1000.0;

        System.out.println("The win rate of the 1927 Yankees is " + winningPercentage + "% rounded to three decimal places.");
    }
}
