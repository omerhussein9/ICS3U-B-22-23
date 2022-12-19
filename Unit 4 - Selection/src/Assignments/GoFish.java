package Assignments;

import java.util.Scanner;

public class GoFish {
    static Scanner in = new Scanner(System.in);

    private static final int NUM_SUITS = 4;
    private static final int RESET_CARDS = -2;

    private static final int NUM_PLAYERS = 4;
    private static final int NUM_VALUES = 13;

    private static final int REQUEST_CARDS = 1;
    private static final int CHECK_HAND = 2;
    private static final int CHECK_SCORE = 3;

    private static final int PLAYER = 1;
    private static final int COM1 = 2;
    private static final int COM2 = 3;
    private static final int COM3 = 4;

    static boolean gameEnded = false;

    static String playerHand = newHand(), comHand1 = newHand(), comHand2 = newHand(), comHand3 = newHand();
    static int playerScore = 0, comScore1 = 0, comScore2 = 0, comScore3 = 0;

    public static void main(String[] args) {
        displayHand(PLAYER);

        checkPairs(playerHand, PLAYER);
        checkPairs(comHand1, COM1);
        checkPairs(comHand2, COM2);
        checkPairs(comHand3, COM3);

        while(!gameEnded) {
            displayHand(PLAYER);
            displayHand(COM1);
            displayHand(COM2);
            displayHand(COM3);

            playerTurn();
            cpuTurn(COM1);
            cpuTurn(COM2);
            cpuTurn(COM3);
        }
    }

    private static void cpuTurn(int p) {
        int playerTargeted = 0;
        while(true) {
            playerTargeted = (int) (Math.random()*4) + 1;
            if(playerTargeted == p) 
                continue;
            break;
        }
        checkHand(playerTargeted, p);
        checkScore(p);
    }

    private static void checkScore(int p) {
    }

    private static void playerTurn() {      
        System.out.println("\nWhat would you like to do?");

        int action = chooseAction();
        int p = choosePlayer(action);

        if(action == REQUEST_CARDS) {
            checkHand(p, PLAYER);

            if(playerScore == 10)
                endGame(PLAYER);
            if(playerHand.length() == 0) {
                System.out.println("Hit 0 cards, drawing 5 new cards.");
                playerHand = newHand();
                checkPairs(playerHand, PLAYER);
            }
        } else if(action == CHECK_HAND) {
            displayHand(p);
            playerTurn();
        } else if(action == CHECK_SCORE) {
            displayScore(p);
            playerTurn();
        }
    }

    private static void endGame(int p) {
    }

    private static void checkHand(int playerTargeted, int requester) {
        String card = "";
        if(requester == PLAYER) 
            card = playerRequestCards();
        else
            card = cpuRequestCards(requester);

        String hand = card + "D";
        if(playerTargeted == PLAYER) hand += playerHand;
        if(playerTargeted == COM1) hand += comHand1;
        if(playerTargeted == COM2) hand += comHand2;
        if(playerTargeted == COM3) hand += comHand3;

        boolean hasRequested = false;
        for (int i = 0; i < hand.length() - 1; i+=2) {
            String c = hand.charAt(i) + "";
            String temp = hand.substring(hand.indexOf(c) + 2);
            if(temp.contains(c)) {
                hasRequested = true;
                hand = hand.substring(0, i) + temp.substring(0, temp.indexOf(c)) + temp.substring(temp.indexOf(c) + 2);
                break;
            }
        }

        if(!hasRequested)
            hand = hand.substring(2); // removes the card added at the beginning of the string to check if player had card

        String requestName = "", targetName = "";
        if(requester == PLAYER) requestName = "Player";
        if(requester == COM1) requestName = "Owen";
        if(requester == COM2) requestName = "Cameron";
        if(requester == COM3) requestName = "Michael";

        if(playerTargeted == PLAYER) {
            playerHand = hand;
            targetName = "Player";
        } if(playerTargeted == COM1) {
            comHand1 = hand;
            targetName = "Owen";
        } if(playerTargeted == COM2) {
            comHand2 = hand;
            targetName = "Cameron";
        } if(playerTargeted == COM3) {
            comHand3 = hand;
            targetName = "Michael";
        }
        System.out.print(requestName + " asking " + targetName + " for " + card + "'s. ");

        if(hasRequested) {
            System.out.println("Match Found!");

            if(requester == PLAYER) checkPairs(playerHand + card + "D", PLAYER);
            if(requester == COM1) checkPairs(comHand1 + card + "D", COM1);
            if(requester == COM2) checkPairs(comHand2 + card + "D", COM2);
            if(requester == COM3) checkPairs(comHand3 + card + "D", COM3);
        } else {
            System.out.println("Go Fish! Drawing a card from the pile.");

            if(requester == PLAYER) {
                playerHand += getCard();
                checkPairs(playerHand, PLAYER);
            } if(requester == COM1) {
                comHand1 += getCard();
                checkPairs(comHand1, COM1);
            } if(requester == COM2) {
                comHand2 += getCard();
                checkPairs(comHand2, COM2);
            } if(requester == COM3) {
                comHand3 += getCard();
                checkPairs(comHand3, COM3);
            }
        }
    }

    private static String playerRequestCards() { // only used for the player
        while(true) {
            System.out.println("\nWhat card would you like to request?");
            
            String temp = "";
            for(int i = 0; i < playerHand.length() - 1; i += 2) {
                if(i != 0)
                    temp += ", ";
                temp += playerHand.charAt(i) + " (" + (i/2 + 1) + ")";
            }
            System.out.print(temp + ": ");

            try {
                int card = Integer.parseInt(in.nextLine());
                if(card < 1 || card > (playerHand.length() / 2))
                    System.out.println("Please enter a valid option.\n");
                else
                    return playerHand.charAt((card - 1) * 2) + "";
            } catch(NumberFormatException e) {
                System.out.println("Please enter a valid option.\n");
            }
        }
    }

    private static String cpuRequestCards(int p) {
        String hand = "";
        if(p == COM1)
            hand = comHand1;
        if(p == COM2)
            hand = comHand2;
        if(p == COM3)
            hand = comHand3;

        int random = (int) (Math.random() * hand.length());
        if(random % 2 != 0) // if random is not even
            random--;

        return hand.charAt(random) + "";
    }

    private static int choosePlayer(int action) {
        String temp = "";
        if(action == REQUEST_CARDS)
            temp = "Which player would you like to ask for cards?";
        if(action == CHECK_HAND)
            temp = "Which player's hand would you like to check?";
        if(action == CHECK_SCORE)
            temp = "Which player's score would you like to check?";

        while(true) {
            System.out.print("\n" + temp + "\nPlayer (1), Owen (2), Cameron (3), Michael (4): ");
            try {
                int p = Integer.parseInt(in.nextLine());

                if(action == REQUEST_CARDS && p == PLAYER)
                    System.out.println("You cannot ask yourself for cards!\n");
                else if(p > 4 || p < 1)
                    System.out.println("Please enter a valid option.\n");
                else
                    return p;
            } catch(NumberFormatException e) {
                System.out.println("Please enter a valid option.\n");
            }
        }
    }

    private static int chooseAction() {
        while(true) {
            System.out.print("Request Cards (1), Check Hands (2), Check Scores (3): ");
            try {
                int action = Integer.parseInt(in.nextLine());

                if(action > 3 || action < 1) 
                    System.out.println("Please enter a valid option.\n");
                else
                    return action;
            } catch(NumberFormatException e) {
                System.out.println("Please enter a valid option.\n");
            }
        }
    }

    private static void checkPairs(String hand, int p) {
        int count = 0;
        String found = "No pairs found.";
        for (int i = 0; i < hand.length() - 1; i+=2) {
            String c = hand.charAt(i) + "";
            String temp = hand.substring(hand.indexOf(c) + 2);
            if(temp.contains(c)) {
                count++;
                found = count + " pair(s) found!";
                hand = hand.substring(0, i) + temp.substring(0, temp.indexOf(c)) + temp.substring(temp.indexOf(c) + 2);
                i = RESET_CARDS;
            }
        } 
        
        String label = "";
        if(p == PLAYER) {
            playerScore += count;
            playerHand = hand;
            label = "Player";
        } else if(p == COM1) {
            comScore1 += count;
            comHand1 = hand;
            label = "Owen";
        } else if(p == COM2) {
            comScore2 += count;
            comHand2 = hand;
            label = "Cameron";
        } else if(p == COM3) {
            comScore3 += count;
            comHand3 = hand;
            label = "Michael";
        }

        System.out.println("Looking for pair(s) in " + label + "'s hand... " + found + "\n");
    }

    public static void displayHand(int p) {              
        if(p == PLAYER) {
            String temp = "";
            for(int i = 0; i < playerHand.length(); i++) {
                if(i % 2 == 0 && i != 0)
                    temp += " ";
                temp += playerHand.charAt(i) + "";
            }
            System.out.println("Player Hand: " + temp);
        } else if(p == COM1) {
            System.out.println("Owen Cards Left: " + comHand1.length() / 2);
        } else if(p == COM2) {
            System.out.println("Cameron Cards Left: " + comHand2.length() / 2);
        } else if(p == COM3) {
            System.out.println("Michael Cards Left: " + comHand3.length() / 2);
        }
    }

    private static void displayScore(int p) {
        if(p == PLAYER)
            System.out.println("Player Score: " + playerScore);
        else if(p == COM1)
            System.out.println("Owen Score: " + comScore1);
        else if(p == COM2)
            System.out.println("Cameron Score: " + comScore2);
        else if(p == COM3)
            System.out.println("Michael Score: " + comScore3);
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
        return "" + iValue;
    }
}