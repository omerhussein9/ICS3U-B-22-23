package Assignments;

import java.util.Scanner;

public class GoFish {
    static Scanner in = new Scanner(System.in);

    private static final int NUM_SUITS = 4;
    private static final int RESET_CARDS = -2;

    private static final int NUM_PLAYERS = 4;
    private static final int NUM_VALUES = 13;

    private static final int PLAYER = 0;
    private static final int COM1 = 0;
    private static final int COM2 = 0;
    private static final int COM3 = 0;

    static String playerHand = newHand(), comHand1 = newHand(), comHand2 = newHand(), comHand3 = newHand();
    static int playerScore = 0, comScore1 = 0, comScore2 = 0, comScore3 = 0;

    public static void main(String[] args) {
        displayHand(playerHand, "Player Hand: ");

        checkPairs(playerHand, PLAYER);

        displayHand(playerHand, "Player Hand: ");
        displayScore(playerScore, "Player Score: ");
    }

    private static void checkPairs(String hand, int p) {
        int count = 0;
        for (int i = 0; i < hand.length() - 1; i+=2) {
            String c = hand.charAt(i) + "";
            String temp = hand.substring(hand.indexOf(c) + 2);
            if(temp.contains(c)) {
                count++;
                hand = hand.substring(0, i) + temp.substring(0, temp.indexOf(c)) + temp.substring(temp.indexOf(c) + 2);
                i = RESET_CARDS;
            }
        }
        
        if(p == PLAYER) {
            playerScore = count;
            playerHand = hand;
        }
        else if(p == COM1) {
            comScore1 = count;
            comHand1 = hand;
        }
        else if(p == COM2) {
            comScore2 = count;
            comHand2 = hand;
        }
        else if(p == COM3) {
            comScore3 = count;
            comHand3 = hand;
        }
    }

    public static void displayHand(String hand, String label) {
        System.out.println(label + hand);
    }

    private static void displayScore(int score, String label) {
        System.out.println(label + score);
    }

    public static String newHand() {
        return getCard() + getCard() + getCard() + getCard() + getCard();
    }

    private static String getCard() {
        return getValue() + getSuit();
    }

    private static String getSuit() {
        int iSuit = (int) Math.round(Math.random() * NUM_SUITS + 1);
        if(iSuit == 1)
            return "H";
        if(iSuit == 2)
            return "S";
        if(iSuit == 3)
            return "C";
        return "D";
    }

    private static String getValue() {
        int iValue = (int) (Math.random() * NUM_VALUES + 1);
        if(iValue == 1)
            return "A";
        else if(iValue == 10)
            return "X";
        else if(iValue == 11)
            return "J";
        else if(iValue == 12) 
            return "Q";
        else if(iValue == 13)
            return "K";
        else
            return "" + iValue;
    }
}