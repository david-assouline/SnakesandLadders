// -----------------------------------------------------
// Written by: David-Raphael Assouline
// -----------------------------------------------------

/* This program simulates a game of ladders and snakes
 * while letting the user choose the amount of players
 * between 2 and 4 inclusively */



import java.util.Scanner;

public class PlayLadderAndSnake {
    public static void main(String[] args) {

        LadderAndSnake example = new LadderAndSnake(amountOfPlayers());
        example.playerSetUp();
        System.out.println("Now deciding which player will start playing.");
        while (example.playersWithoutPosition.size() != 1) {
            example.turnOrder();

        }
        example.lastTurnOrder();
        System.out.println("\n*************************\nGAME IS STARTING\n*************************\n");
        while (!example.winCondition) {
            example.play();
        }

    }

    /**
    * Returns an the amount of players that the user has selected
    * The amount of players must be between 2 and 4 inclusively, or else the program will terminate after the 4th
    * unsuccesful attempt
    * @return   the amount of players that will play snakes and ladders
    */
    public static int amountOfPlayers() {
        System.out.println("Hello and welcome to Snakes And Ladders, COMP-249 edition!\n");
        System.out.println("Enter the # of players for your game. The number must be between 2 and 4 inclusively:");
        Scanner userInput = new Scanner(System.in);
        int z = userInput.nextInt();
        int counter = 0;
        while (z < 2 || z > 4) {
            counter += 1;
            if (counter == 4) {
                System.out.println("Bad attempt 4! You have exhausted all your chances. Program will terminate!");
                System.exit(0);
            }
            System.out.println("Bad attempt " + counter + " - Invalid # of players. Please enter a # between 2 and 4 inclusively: ");
            z = userInput.nextInt();
        }
         return z;
    }


}
