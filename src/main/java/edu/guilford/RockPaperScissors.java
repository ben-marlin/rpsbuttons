package edu.guilford;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);   // instantiate scanner
        Random random = new Random();               // instantiate randomizer
        
        String[] rps = {"rock", "paper", "scissors"};   // convenience for later
        String playerMove, computerMove;                // holds throws
        boolean playAgain = true;                       // used for while loop
        
        while (playAgain) {
            System.out.print("Enter your move (r for Rock, p for Paper, s for Scissors): ");
            playerMove = scanner.nextLine().toLowerCase();

            // validate input - only r, p, or s are usable
            while (!playerMove.equals("r") && !playerMove.equals("p") && !playerMove.equals("s")) {
                System.out.print("Invalid move. Please enter r, p, or s: ");
                playerMove = scanner.nextLine().toLowerCase();
            }

            // convert throw to string for printing
            switch (playerMove) {
                case "r":
                    playerMove = "rock";
                    break;

                case "p":
                    playerMove = "paper";
                    break;
            
                case "s":
                    playerMove = "scissors";
                    break;
                    
                default:
                    break;
            }
            
            // print player throw
            System.out.println("Your throw: " + playerMove);

            // generate computer throw
            computerMove = rps[random.nextInt(3)];

            // print computer throw
            System.out.println("Computer throw: " + computerMove);
            
            // decide result
            if (playerMove.equals(computerMove)) {
                System.out.println("It's a tie!");
            } else if ((playerMove.equals("r") && computerMove.equals("s")) ||  // rock dulls scissors
                       (playerMove.equals("p") && computerMove.equals("r")) ||  // paper covers rock
                       (playerMove.equals("s") && computerMove.equals("p"))) {  // scissors cut paper
                System.out.println("You win!");
            } else {
                System.out.println("You lose!");
            }
            
            System.out.print("Do you want to play again? (y/n): ");         // determine whether to repeat
            char response = scanner.nextLine().toLowerCase().charAt(0);
            playAgain = (response == 'y');
        }
        
        scanner.close();
        System.out.println("Thanks for playing!");
    }
}
