package Assignments;

import java.util.Scanner;

public class Blackjack {
    
    static Scanner in = new Scanner(System.in);
    static final int MIN_BET = 5;
    static final int STARTING_WALLET = 500;
    static final int WIN = 1;
    static final int LOST = -1;
    static final int TIE = 0;
    static final int NUM_SUITS = 4;
    private static final String HEARTS = "H";
    private static final String SPADES = "S";
    private static final String CLUBS = "C";
    private static final String DIAMONDS = "D";
    private static final int NUM_VALUES = 13;
    private static final String ACE = "A";
    private static final String JACK = "J";
    private static final String QUEEN = "Q";
    private static final String KING = "K";


    public static void main(String[] args) {
        int wallet = STARTING_WALLET;
        boolean stillPlaying = true;

        while(stillPlaying) {
            int bet = getBet(wallet);
            String playerHand = getCard() + " " + getCard();
            String dealerHand = getCard();

            displayHand(playerHand, false, "Player: ");
            displayHand(dealerHand, true, "Dealer: ");

            int result = playHand(playerHand, dealerHand);

            if(result == WIN)
                wallet += bet;
            else if(result == LOST)
                wallet -= bet;

            if(wallet < MIN_BET) {
                stillPlaying = false;
                System.out.println("Game Over. Insuffiecent funds.");
            }
            else   
                stillPlaying = playAgain();
        }
    }

    private static boolean playAgain() {
        return false;
    }

    //return WIN if player wins, LOST if player lost, TIE if player ties
    private static int playHand(String playerHand, String dealerHand) {
        return 0;
    }


    private static void displayHand(String cards, boolean isHidden, String label) {
        String extra = isHidden ? "XX " : "";
        System.out.println(label + extra + cards);
    }


    private static String getCard() {
        return getValue() + getSuit();
    }


    private static String getSuit() {
        int iSuit = (int) Math.round(Math.random() * NUM_SUITS + 1);
        return iSuit == 1 ? HEARTS : iSuit == 2 ? SPADES : iSuit == 3 ? CLUBS : DIAMONDS;
    }

    private static String getValue() {
        int iValue = (int) (Math.random() * NUM_VALUES + 1);

        if(iValue == 1)
            return ACE;
        else if(iValue == 11)
            return JACK;
        else if(iValue == 12) 
            return QUEEN;
        else if(iValue == 13)
            return KING;
        else
            return "" + iValue;
    }

    private static int getBet(int maxBet) {
        boolean validBet = false;
        int bet = 0;
        System.out.print("Please enter bet (MIN: $" + MIN_BET + "): ");
        while(!validBet) {
            try {
                bet = Integer.parseInt(in.nextLine());
                if(bet > maxBet)
                    System.out.print("Please enter bet (MAX: $" + maxBet + "): ");
                else if(bet < MIN_BET)
                    System.out.print("Please enter bet (MIN: $" + MIN_BET + "): ");
                else 
                    validBet = true;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid Bet");
                System.out.print("Please enter bet (MIN: $" + MIN_BET + "): ");
            }
        }
        return bet;
    }
}
