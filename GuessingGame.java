package jpk;
// Students: Jake Danson, Garret Kier
// Due Date: 10/04/2019
// Class: CS145
// Assignment: Lab 1: Guessing game
// Purpose: Plays a game where the user guesses a number between 1 and 100 picked randomly from the computer.
// Extra Credit: Used case switch statement, made program bulletproof. <-----------------------------------------------

import java.util.*;

public class GuessingGame{
    private static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        intro();
        playGame(10000,0,0); // Static numbers (guess, best guess, games, totalGuess) // DON'T CHANGE

    } // end of main

    public static void playGame(int bestGuess, int games, int totalGuess) { // Plays game
        int guess = 0; // After each game is played, sets guess back to zero.
        int num = 0;
        Random rand = new Random();
        int randomNum = rand.nextInt(99) + 1; // Sets random number between 1 and 100
        System.out.println("I'm thinking of a number between 1 and 100...");
        num = bulletProofInt();
        if(num != randomNum) {
            while (num != randomNum) { // Loop that goes until user guesses the random number

                if (num > randomNum) { // If guess is higher, guess increases.
                    guess++;
                    System.out.println("Guess Lower!");
                    System.out.println("Total guesses: " + guess);
                    num = bulletProofInt();
                } else if (num < randomNum) { // If guess is lower, guess increases
                    guess++;
                    System.out.println("Guess Higher!");
                    System.out.println("Total guesses: " + guess);
                    num = bulletProofInt();
                } else { // Breaks out of if loop if guess == randomNum
                    break;
                }
                if (guess < bestGuess) { // Changes best guess equal to actual best guess
                    bestGuess = guess;
                }

            } // end of while
        } else{
            bestGuess = 1;
            totalGuess++;
        }// end of if

        if (num == randomNum) { // If user guess == randomNum then asks to play again, or reports stats to console.
            totalGuess = guess + totalGuess;
            games++;
            System.out.println("Would you like to play again?     Y/N ?");
            String question = scan.next();
            boolean bool = true;
            while(bool) {
                switch (question.toLowerCase()) {
                    case "y":
                        playGame(bestGuess, games, totalGuess);
                        bool = false;
                        break;
                    case "n":
                        reportStats(games, totalGuess, bestGuess);
                        bool = false;
                        break;
                    default:
                        System.out.println("Please enter the correct answer!\nY or N?");
                        question = scan.next();
                } // end of switch
            } // end of while
        } // end of if
    } // end of playGame

        public static void reportStats(int games, int guesses, int bestGuess){ // Prints overall game stats
            System.out.println("Here are your stats overall: ");
            System.out.println("Games Played: " + games);
            System.out.println("Total Guesses: " + guesses);
            System.out.println("Best guess: " + bestGuess);
            System.out.println("Average Guess to win: " + (double)guesses/games);

        } // end of reportStats

        public static void intro(){ // prints Introduction
            System.out.println("This program allows you to play a guessing game.");
            System.out.println("I will think of a number between 1 and 100 and");
            System.out.println("will allow you to guess until you get it right.");
            System.out.println("For each guess, I will tell you whether the right");
            System.out.println("answer is higher or lower than your guess.");
            System.out.println();

        } // end of intro method
        public static int bulletProofInt(){ // Makes it so the scanner only takes in an integer and does not crash programf
            while(!scan.hasNextInt()){
                scan.next();
                System.out.println("Please enter a positive integer!");
            }
            return scan.nextInt();
        }// end of bulletProofInt
    }// end of class