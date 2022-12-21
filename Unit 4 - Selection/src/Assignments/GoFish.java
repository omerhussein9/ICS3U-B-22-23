package Assignments;

import java.util.Scanner;

public class GoFish {
    static Scanner in = new Scanner(System.in);

    private static final int NUM_SUITS = 4;
    private static final int NUM_VALUES = 13;
    private static final int RESET_CARDS = -2;

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
        gameEnded = false;
        displayHand(PLAYER);

        checkPairs(playerHand, PLAYER);
        checkPairs(comHand1, COM1);
        checkPairs(comHand2, COM2);
        checkPairs(comHand3, COM3);

        while(!gameEnded) {
            displayAllPlayer(CHECK_HAND);

            playerTurn();
            comTurn(COM1);
            comTurn(COM2);
            comTurn(COM3);
        }
    }

    private static void comTurn(int p) {
        if(!gameEnded) {
            int playerTargeted = 0;
            while(true) {
                playerTargeted = (int) (Math.random()*4) + 1;
                if(playerTargeted != p)
                    break;
            } 
            // chooses a random player for the com to target other than itself
            checkHand(playerTargeted, p);
            outOfCards();
        }
    }

    private static void checkScore() { // checks to see if any players score is 10 or above
        if(playerScore >= 10)
            endGame(PLAYER);
        else if(comScore1 >= 10)
            endGame(COM1);
        else if(comScore2 >= 10)
            endGame(COM2);
        else if(comScore3 >= 10)
            endGame(COM3);
    }

    private static void playerTurn() {      
        System.out.println("\nWhat would you like to do?");

        int action = chooseAction();

        if(action == REQUEST_CARDS) {
            int p = choosePlayer();
            checkHand(p, PLAYER);
            outOfCards();
        } else if(action == CHECK_HAND) {
            displayAllPlayer(CHECK_HAND);
            playerTurn(); // asks for the action again
        } else if(action == CHECK_SCORE) {
            displayAllPlayer(CHECK_SCORE);
            playerTurn(); // asks for the action again
        }
    }

    private static void outOfCards() {
        if(playerHand.length() == 0 || comHand1.length() == 0 || comHand2.length() == 0 || comHand3.length() == 0) {
            System.out.println("Hit 0 cards, drawing 5 new cards.");
            if(playerHand.length() == 0) {
                playerHand = newHand();
                checkPairs(playerHand, PLAYER);
            } if(comHand1.length() == 0) {
                comHand1 = newHand();
                checkPairs(comHand1, COM1);
            } if(comHand2.length() == 0) {
                comHand2 = newHand();
                checkPairs(comHand2, COM2);
            } if(comHand3.length() == 0) {
                comHand3 = newHand();
                checkPairs(comHand3, COM3);
            }
        }
    }

    private static void endGame(int p) {
        System.out.println();

        String label = "";
        if(p == PLAYER) 
            label = "Player";
        if(p == COM1)
            label = "Owen";
        if(p == COM2)
            label = "Cameron";
        if(p == COM3)
            label = "Michael";

        displayAllPlayer(CHECK_SCORE);
        
        System.out.println(label + " has reached 10 points! " + label + " wins the game!\n");

        if(playAgain()) {
            playerHand = newHand(); comHand1 = newHand(); comHand2 = newHand(); comHand3 = newHand();
            playerScore = 0; comScore1 = 0; comScore2 = 0; comScore3 = 0;

            main(null); // calls the main function, restarting the game.
        }
        gameEnded = true; // when you dont play again it sets this to true and stops the com's from playing, ending the loop
    }

    private static boolean playAgain() {
        while(true) {
            System.out.print("Would you like to play again? (Y/N): ");
            String line = in.nextLine();
            if(line.equals("Y") || line.equals("y") || line.equals("yes") || line.equals("YES"))
                return true;
            else if(line.equals("N") || line.equals("n") || line.equals("no") || line.equals("NO"))
                return false;
            else
                System.out.println("Invalid Response");
        }
    }

    private static void checkHand(int playerTargeted, int requester) {
        String card = "";
        if(requester == PLAYER)  card = playerRequestCards();
        else card = comRequestCards(requester);

        String hand = card + "D"; // adds a random suit afterwards - card only has the value and not the suit
        if(playerTargeted == PLAYER) hand += playerHand; // adds the hand after the card added
        if(playerTargeted == COM1) hand += comHand1;
        if(playerTargeted == COM2) hand += comHand2;
        if(playerTargeted == COM3) hand += comHand3;

        boolean hasRequested = false;
        for (int i = 0; i < hand.length() - 1; i+=2) { // almost the same as check pairs
            String c = hand.charAt(i) + "";
            String temp = hand.substring(hand.indexOf(c) + 2);
            if(temp.contains(c)) {
                hasRequested = true;
                hand = hand.substring(0, i) + temp.substring(0, temp.indexOf(c)) + temp.substring(temp.indexOf(c) + 2);
                break; // breaks after finding pair since there should only be one.
            }
        }

        if(!hasRequested)
            hand = hand.substring(2); // removes the card added at the beginning of the string to check if player had card

        String requestName = "", targetName = ""; // to make it look nice
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

        if(hasRequested) { // adds the card to hand and checks for pairs (there is always a pair)
            System.out.println("Match Found!");

            if(requester == PLAYER) checkPairs(playerHand + card + "D", PLAYER);
            if(requester == COM1) checkPairs(comHand1 + card + "D", COM1);
            if(requester == COM2) checkPairs(comHand2 + card + "D", COM2);
            if(requester == COM3) checkPairs(comHand3 + card + "D", COM3);

            checkScore();
        } else {
            System.out.println("Go Fish! Drawing a card from the pile.");

            if(requester == PLAYER) {
                playerHand += getCard(); // adds a random new card to the hand
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
        String temp = "";
        for(int i = 0; i < playerHand.length() - 1; i += 2) {
            if(i != 0)
                temp += ", ";
            temp += playerHand.charAt(i) + " (" + (i/2 + 1) + ")"; // adds the numbers next to the card for choosing
        }

        while(true) {
            System.out.print("\nWhat card would you like to request?\n" + temp + ": ");

            try {
                int card = Integer.parseInt(in.nextLine());
                if(card < 1 || card > (playerHand.length() / 2)) // each card is 2 in length so /2 gives # of cards
                    System.out.println("Please enter a valid option.\n");
                else
                    return playerHand.charAt((card - 1) * 2) + ""; // -1 on the card b/c index starts at 0, *2 for string index
            } catch(NumberFormatException e) {
                System.out.println("Please enter a valid option.\n");   
            }
        }
    }

    private static String comRequestCards(int p) {
        String hand = "";
        if(p == COM1)
            hand = comHand1;
        if(p == COM2)
            hand = comHand2;
        if(p == COM3)
            hand = comHand3;

        int random = (int) (Math.random() * hand.length());
        if(random % 2 != 0)
            random--; // if random is not even, makes it even (card values always have an even index)

        return hand.charAt(random) + "";
    }

    private static int choosePlayer() {
        String temp = "Which player would you like to ask for cards?";

        while(true) {
            System.out.print("\n" + temp + "\nOwen (1), Cameron (2), Michael (3): ");
            try {
                int p = Integer.parseInt(in.nextLine());

                if(p > 3 || p < 1)
                    System.out.println("Please enter a valid option.\n");
                else
                    return p + 1; // +1 b/c com values are 1 higher than the options shown
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
            String temp = hand.substring(hand.indexOf(c) + 2); // string without current card and everything before it
            if(temp.contains(c)) {
                count++;
                found = count + " pair(s) found!"; // to make the interface look nice
                hand = hand.substring(0, i) + temp.substring(0, temp.indexOf(c)) + temp.substring(temp.indexOf(c) + 2);
                i = RESET_CARDS; // resets the loop to make i = 0 again
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
        if(p == PLAYER)
            System.out.println("Player Hand: " + addSpaces(playerHand, false));
        if(p == COM1)
            System.out.println("Owen Hand: " + addSpaces(comHand1, true));
        if(p == COM2)
            System.out.println("Cameron Hand: " + addSpaces(comHand2, true));
        if(p == COM3)
            System.out.println("Michael Hand: " + addSpaces(comHand3, true));
    }

    private static String addSpaces(String hand, boolean isHidden) { // adds spaces after every card to make it look nice
        String temp = "";
        for(int i = 0; i < hand.length(); i++) {
            if(i % 2 == 0 && i != 0)
                temp += " ";
            if(isHidden)
                temp += "X";
            else 
                temp += hand.charAt(i) + "";
        }
        return temp;
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

    private static void displayAllPlayer(int action) { // better in some scenarios
        if(action == CHECK_HAND) {
            displayHand(PLAYER); displayHand(COM1); displayHand(COM2); displayHand(COM3);
        } else if (action == CHECK_SCORE) {
            displayScore(PLAYER); displayScore(COM1); displayScore(COM2); displayScore(COM3);
        }
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