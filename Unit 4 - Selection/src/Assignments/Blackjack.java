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
    static final int BLACK_JACK = 21;
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

            if(result == WIN) {
                wallet += bet;
                System.out.println("Player WINS!");
            }
            else if(result == LOST) {
                wallet -= bet;
                System.out.println("Player LOSES...");
            }
            if(result == TIE)
                System.out.println("Player and Dealer TIE!");

            if(wallet < MIN_BET) {
                stillPlaying = false;
                System.out.println("Game Over. Insuffiecent funds.");
            }
            else
                stillPlaying = playAgain(wallet);
        }
    }

    private static boolean playAgain(int wallet) {
        System.out.println("You have $" + wallet + " left.");
        while(true) {
            System.out.print("Would you like to play again? (Y/N): ");
            String line = in.nextLine();
            if(line.equals("Y") || line.equals("y") || line.equals("yes") || line.equals("YES"))
                return true;
            else if(line.equals("N") || line.equals("n") || line.equals("no") || line.equals("NO"))
                return false;
            else {
                System.out.println("Invalid Response");
            }
        }
    }

    //return WIN if player wins, LOST if player lost, TIE if player ties
    private static int playHand(String playerHand, String dealerHand) {
        playerHand = playerTurn(playerHand);
        dealerHand = dealerTurn(dealerHand);

        int playerScore = getCardsValue(playerHand);
        int dealerScore = getCardsValue(dealerHand);

        if(dealerScore <= BLACK_JACK && (dealerScore > playerScore || playerScore > BLACK_JACK))
            return LOST;
        if((playerScore > dealerScore && playerScore <= BLACK_JACK) || dealerScore > BLACK_JACK) 
            return WIN;
        return TIE;
    }

    private static int getCardsValue(String cards) {
        int numAces = 0;

        int scoreBeforeAces = 0;

        for(int i = 0; i < cards.length(); i++) {
            String s = cards.substring(i,i + 1);
            if("JQK1".indexOf(s) >= 0)
                scoreBeforeAces += 10;
            else if("23456789".indexOf(s) >= 0)
                scoreBeforeAces += Integer.parseInt(s);
            else if(s.equals("A"))
                numAces++;
        }

        if(numAces > 0 && ((scoreBeforeAces + 11 + numAces - 1) <= BLACK_JACK))
            scoreBeforeAces += 11 + numAces - 1;
        else   
            scoreBeforeAces += numAces;
        
        return scoreBeforeAces;
    }

    private static String dealerTurn(String dealerHand) {
        dealerHand += " " + getCard();
        displayHand(dealerHand, false, "Dealer Hand: ");
        while(getCardsValue(dealerHand) < 17) {
            dealerHand += " " + getCard();
            displayHand(dealerHand, false, "Dealer Hand: ");
        }

        return dealerHand;
    }

    private static String playerTurn(String playerHand) {
        displayHand(playerHand, false, "Player Hand: ");

        while(true) {
            if(takeCard()) {
                playerHand += " " + getCard();
                displayHand(playerHand, false, "Player Hand: ");
                if(getCardsValue(playerHand) > BLACK_JACK)
                    return playerHand;
            }
            else 
                return playerHand;
        }
    }

    private static boolean takeCard() {
        while(true) {
            System.out.print("Hit (1) or Stand (2): ");
            String result = in.nextLine().toLowerCase();

            if(result.equals("1"))
                return true;
            else if(result.equals("2"))
                return false;
            else
                System.out.print("Invalid Answer");
        }
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
