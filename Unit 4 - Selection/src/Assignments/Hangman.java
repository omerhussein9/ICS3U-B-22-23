package Assignments;

import java.util.Scanner;

public class Hangman {

    public static final int MAX_BODY_PARTS = 7;
    public static Scanner in = new Scanner(System.in);
    public static final String VALID_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        String phrase = getPhrase();
        int numWrong = 0;
        String chosenLetters = "";

        while(numWrong < MAX_BODY_PARTS) {
            displayEncodedPhrase(phrase, chosenLetters);

            String letter = getLetter(chosenLetters);
            chosenLetters += letter;

            if(!isInPhrase(letter,phrase)) {
                numWrong++;
                drawHangman(numWrong);
            }

            if(numWrong == MAX_BODY_PARTS)
                System.out.println("You Lose!!!! GET BETTER AT THE GAME YOUR DOGWATER FREER THAN AN ORPHANAGE");
        }
    }

    private static boolean isInPhrase(String letter, String phrase) {
        return phrase.indexOf(letter) >= 0;
    }

    private static String getLetter(String chosenLetters) {
        while(true) {
            System.out.println("Please enter a letter. Chosen letters are: " + getLettersString(chosenLetters));
            String letter = in.nextLine().toUpperCase();

            if(letter.length() > 1)
                System.out.println("Only one letter please!");
            else if(VALID_CHARS.indexOf(letter) < 0)
                System.out.println("Only valid letters (A-Z)!!!!");
            else if(chosenLetters.indexOf(letter) >= 0)
                System.out.println("Please enter a letter you haven't chosen!!!!");
            else
                return letter;
        }
    }

    private static String getLettersString(String chosenLetters) {
        String result = "";
        for(int i = 0; i < chosenLetters.length(); i++) {
            result += chosenLetters.substring(i,i + 1) + " ";
        }
        return result;
    }

    private static void displayEncodedPhrase(String phrase, String chosenLetter) {
        String result = "";
        for (int i = 0; i < phrase.length(); i++) {
            String letter = phrase.substring(i,i + 1);
            if(letter.equals(" "))
                result += "/";
            else if(chosenLetter.indexOf(letter) >= 0)
                result += (letter + " ");
            else
                result += "_ ";
        }
        System.out.println(result);
    }

private static String getPhrase() {
        boolean validPhrase = false;
        String phrase = "";
        while(!validPhrase) {
            System.out.println("Pleaser enter a valid phrase (letters and spaces only): ");
            phrase = in.nextLine().toUpperCase();
            validPhrase = true;
            for(int i = 0; i < phrase.length(); i++) {
                String letter = phrase.substring(i, i+1);
                if((VALID_CHARS + " ").indexOf(letter) < 0)
                    validPhrase = false;
            }
        }
        return phrase;
    }

    public static void drawHangman(int numBodyParts) {
        if(numBodyParts==7){
            System.out.println(" ");
            System.out.println(" _________");
            System.out.println("|         |");
            System.out.println("|         o");
            System.out.println("|        /|\\");
            System.out.println("|         |");
            System.out.println("|       _/ \\_");
            System.out.println("|");
            System.out.println("------------------");
        }else if(numBodyParts==6){
            System.out.println(" ");
            System.out.println(" _________");
            System.out.println("|         |");
            System.out.println("|         o");
            System.out.println("|        /|\\");
            System.out.println("|         |");
            System.out.println("|       _/");
            System.out.println("|");
            System.out.println("------------------");
        }else if(numBodyParts==5){
            System.out.println(" ");
            System.out.println(" _________");
            System.out.println("|         |");
            System.out.println("|         o");
            System.out.println("|        /|\\");
            System.out.println("|         |");
            System.out.println("|");
            System.out.println("|");
            System.out.println("------------------");
        }else if(numBodyParts==4){
            System.out.println(" ");
            System.out.println(" _________");
            System.out.println("|         |");
            System.out.println("|         o");
            System.out.println("|        /|\\");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("------------------");
        }else if(numBodyParts==3){
            System.out.println(" ");
            System.out.println(" _________");
            System.out.println("|         |");
            System.out.println("|         o");
            System.out.println("|        /|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("------------------");
        }else if(numBodyParts==2){
            System.out.println(" ");
            System.out.println(" _________");
            System.out.println("|         |");
            System.out.println("|         o");
            System.out.println("|         |");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("------------------");
        }else if(numBodyParts==1){
            System.out.println(" ");
            System.out.println(" _________");
            System.out.println("|         |");
            System.out.println("|         o");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("------------------");
        }else if(numBodyParts==0){
            System.out.println(" ");
            System.out.println(" _________");
            System.out.println("|         |");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("|");
            System.out.println("------------------");
        }
        
    }
}
