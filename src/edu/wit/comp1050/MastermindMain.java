package edu.wit.comp1050;

import java.io.*;
import java.util.*;
public class MastermindMain {

    private BufferedReader reader = new BufferedReader (new InputStreamReader (System.in));
    private String[] guess = new String[4]; //Blue Orange Green Purple
    private String[] secret = new String[4];
    private boolean[] secretFound = new boolean[4];
    private int guessCount = 12;
    //secret[0] >> secret[1]
    //secretFound[0] = true; secretFound[1] = false;
    private int turn = 1;


    public static void main(String[] args) throws IOException {
        new MastermindMain();
    }
    MastermindMain() throws IOException {
        File pattern = new File("edu/wit/comp1050/pattern.txt");
        Scanner readPattern = new Scanner(pattern);
        Scanner readGuess = new Scanner(System.in);

        secretFound[0] = false;
        secretFound[1] = false;
        secretFound[2] = false;
        secretFound[3] = false;
        System.out.println ("Hello, Welcome To The Game Of Mastermind!");


        // secret = {(w,x,y,z): w,x,y,z € {1,2,3,4,5,6}} //
        secret[0] = readPattern.next();
        secret[1] = readPattern.next();
        secret[2] = readPattern.next();
        secret[3] = readPattern.next();


        // print the answer //

        System.out.println("The secret 1: " + secret[0] + " The secret 2: " + secret[1] + " The secret 3: " + secret[2] + " The secret 4: " + secret[3]);
        do {


            // ask for quess //
                do {
                    System.out.println ("Enter your guess: ");
                    guessCount++;
                    String peg1 = readGuess.next(); //Blue >> Green
                    //Green Blue Yellow Orange >> White peg
                    String peg2 = readGuess.next(); //Blue >> Blue
                    //Green Blue Yellow Orange >> Black peg
                    String peg3 = readGuess.next(); //Red >> Yellow
                    //Green Blue Yellow Orange >> No peg
                    String peg4 = readGuess.next();
                    //check if all black pegs >> user wins
                   guess[guessCount] = peg1 + " " + peg2 + " " + peg3 + " " + peg4;

                } while ((secretFound[0] != true && secretFound[1] != true && secretFound[2] != true && secretFound[3] != true) || guessCount <= 12);




            // white and black pegs //

            boolean[] white = new boolean[4], black = new boolean[4];
            Arrays.fill (white, false);
            Arrays.fill (black, false);

            for (int i = 0; i < white.length; i++) {
                black[i] = guess[i] == secret[i];
                if (!black[i]) {
                    int j = 0;
                    for (int k = 0; k < 3; k++, j++) {
                        if (j == i)
                            j++;
                        white[i] = white[i] || (guess[i] == secret[j]);
                    }
                }
            }

            int whitepegs = 0, blackpegs = 0;

            for (int i = 0; i < white.length; i++) {

                if (white[i])
                    whitepegs++;
                if (black[i])
                    blackpegs++;
            }
            System.out.println ("You have " + whitepegs + " white peg" + (whitepegs == 1 ? "" : "s") + " and " + blackpegs + " black peg" + (blackpegs == 1 ? "" : "s"));

            turn ++;
        }
        while (!(secret[0] == guess[0] && secret[1] == guess[1] && secret[2] == guess[2] && secret[3] == guess[3]) && turn <= 8);
        System.out.println ("Congratulations, you've win! ");
    }
}
