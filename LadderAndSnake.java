// -----------------------------------------------------
// Assignment # 1
// Part I
// Written by: David-Raphael Assouline 40082595
// -----------------------------------------------------

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class LadderAndSnake {

    int[][] board = new int[10][10];
    Random rand = new Random();
    public int numberOfPlayers = 0;
    ArrayList<String> playersWithoutPosition = new ArrayList<String>();
    ArrayList<String> finalOrder = new ArrayList<String>();
    int assignedPosition = 1;
    boolean winCondition = false;
    int player1 = 0;
    int player2 = 0;
    int player3 = 0;
    int player4 = 0;

    /**
     * Constructor method that generates a 10x10 board of 100 squares and sets the amount of players
     * The amount of players is determined in a seperate method from the main class
     * unsuccesful attempt
     *
     * @param amountOfPlayers the amount of players that will play snakes and ladders
     */
    public LadderAndSnake(int amountOfPlayers) {
        this.numberOfPlayers = amountOfPlayers;
        int x = 1;
        for (int i = 0; i < 10; i += 1) {
            board[9][i] = x;
            x += 1;
        }
        x = 11;
        for (int i = 9; i > -1; i -= 1) {
            board[8][i] = x;
            x += 1;
        }
        x = 21;
        for (int i = 0; i < 10; i += 1) {
            board[7][i] = x;
            x += 1;
        }
        x = 31;
        for (int i = 9; i > -1; i -= 1) {
            board[6][i] = x;
            x += 1;
        }
        x = 41;
        for (int i = 0; i < 10; i += 1) {
            board[5][i] = x;
            x += 1;
        }
        x = 51;
        for (int i = 9; i > -1; i -= 1) {
            board[4][i] = x;
            x += 1;
        }
        x = 61;
        for (int i = 0; i < 10; i += 1) {
            board[3][i] = x;
            x += 1;
        }
        x = 71;
        for (int i = 9; i > -1; i -= 1) {
            board[2][i] = x;
            x += 1;
        }
        x = 81;
        for (int i = 0; i < 10; i += 1) {
            board[1][i] = x;
            x += 1;
        }
        x = 91;
        for (int i = 9; i > -1; i -= 1) {
            board[0][i] = x;
            x += 1;
        }


    }

    /**
     * This method generates the names of the players and stores them in a playersWithoutPosition variable
     * based on the number of players previously selected.
     */
    public void playerSetUp() {

        System.out.println("Game is played by " + this.numberOfPlayers + " players");

        if (numberOfPlayers == 2) {
            playersWithoutPosition.add("Donald");
            playersWithoutPosition.add("Joe");
        } else if (numberOfPlayers == 3) {
            playersWithoutPosition.add("Donald");
            playersWithoutPosition.add("Joe");
            playersWithoutPosition.add("Mike");
        } else if (numberOfPlayers == 4) {
            playersWithoutPosition.add("Donald");
            playersWithoutPosition.add("Joe");
            playersWithoutPosition.add("Mike");
            playersWithoutPosition.add("Kamala");
        }
    }

    /**
     * This method will roll a dice number from 1 to 6 and will assign the playing order bases on the highest roll.
     * In the event of a tie, the tied players will continue rolling the dice until a winner is determined
     */
    public void turnOrder() {
        Random rand = new Random();
        int localMax = -1;
        String leadingPlayer = "";
        boolean isTie = false;

        ArrayList<String> tiedPlayers = new ArrayList<String>();
        System.out.println("\n*************************\nRolling dice for turn #" + assignedPosition + "\n*************************\n");
        for (int i = 0; i < playersWithoutPosition.size(); i += 1) {
            int x = (rand.nextInt(6) + 1);
            if (x > localMax) {
                localMax = x;
                leadingPlayer = playersWithoutPosition.get(i);
                isTie = false;
            } else if (x == localMax) {
                isTie = true;
                tiedPlayers.add(playersWithoutPosition.get(i));
            }
            System.out.println(playersWithoutPosition.get(i) + " rolled a " + x);


        }
        if (isTie) {
            tiedPlayers.add(leadingPlayer);
            if (tiedPlayers.size() == 2) {
                System.out.println("\n*************************\nA tie was achieved between " + tiedPlayers.get(0) + " and " + tiedPlayers.get(1) + ". Attempting to break the tie.\n*************************\n");
            } else if (tiedPlayers.size() == 3) {
                System.out.println("\n*************************\nA tie was achieved between " + tiedPlayers.get(0) + " and " + tiedPlayers.get(1) + " and " + tiedPlayers.get(2) + ". Attempting to break the tie.\n*************************\n");
            } else if (tiedPlayers.size() == 4) {
                System.out.println("\n*************************\nA tie was achieved between " + tiedPlayers.get(0) + " and " + tiedPlayers.get(1) + " and " + tiedPlayers.get(2) + " and " + tiedPlayers.get(3) + ". Attempting to break the tie.\n*************************\n");
            }
            while (isTie) {
                localMax = -1;
                leadingPlayer = "";
                boolean localTie = true;

                for (int i = 0; i < tiedPlayers.size(); i += 1) {
                    int x = (rand.nextInt(6) + 1);
                    if (x > localMax) {
                        localMax = x;
                        leadingPlayer = tiedPlayers.get(i);
                        localTie = false;
                    } else if (x == localMax) {
                        localTie = true;
                    }
                    System.out.println(tiedPlayers.get(i) + " rolled a " + x);


                }
                if (localTie == false) {
                    isTie = false;
                } else if (localTie) {
                    System.out.println("\n*************************\nAnother tie was achieved. Attempting to break the tie.\n*************************\n");
                }
            }


        }
        System.out.println("\nRank #" + assignedPosition + " in the playing order goes to " + leadingPlayer);
        finalOrder.add(leadingPlayer);
        playersWithoutPosition.remove(leadingPlayer);
        assignedPosition += 1;


    }

    /**
     * This method will assign the last rank in the playing order to the last place dice roller by default
     * and will print out to the console the final order of the players.
     */
    public void lastTurnOrder() {
        System.out.println("\nBy default, Rank #" + assignedPosition + " in the playing order goes to " + playersWithoutPosition.get(0));
        finalOrder.add(playersWithoutPosition.get(0));
        playersWithoutPosition.remove(0);
        assignedPosition += 1;
        System.out.println("*************************\nFinal playing order has been decided\n");
        for (int i = 0; i < finalOrder.size(); i += 1) {
            System.out.println("#" + (i + 1) + " " + finalOrder.get(i));
        }
        System.out.println("*************************");

    }

    /**
     * This method will print out the board
     */
    public void display() {
        for (int i = 0; i < 10; i += 1) {
            for (int j = 0; j < 10; j += 1) {
                System.out.print(this.board[i][j] + "    ");
            }
            System.out.println();
        }

    }

    /**
     * This method will return a random value between 1 and 6
     *
     * @return a random value between 0 and 6
     */
    public int flipDice() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    /**
     * This method will play a single turn for each player in the game by using the flipDice() method.
     * In the event a player lands on square 100, the program will terminate
     */
    public void play() {
        for (int i = 0; i < finalOrder.size(); i += 1) {

            if (i == 0) {
                int x = flipDice();
                System.out.println(finalOrder.get(i) + " is on square " + player1 + " and rolled a: " + x);
                player1 = outcome(player1 + x);
                if (player1 == 100) {
                    System.out.println(finalOrder.get(i) + " wins the game!");
                    winCondition = true;
                    System.out.println("\nThank you all for playing. Terminating the program.");
                    System.exit(0);
                } else if (player1 > 100) {
                    int previousScore = player1 - x;
                    player1 = outcome(-(x + previousScore - 200));
                    System.out.println("Your excess amount was " + (x + previousScore - 100) + " you have been moved to square " + player1);

                }
                System.out.println("\n");
            }
            if (i == 1) {
                int x = flipDice();
                System.out.println(finalOrder.get(i) + " is on square " + player2 + " and rolled a: " + x);
                player2 = outcome(player2 + x);
                if (player2 == 100) {
                    System.out.println(finalOrder.get(i) + " wins the game!");
                    winCondition = true;
                    System.out.println("\nThank you all for playing. Terminating the program.");
                    System.exit(0);
                } else if (player2 > 100) {
                    int previousScore = player2 - x;
                    player2 = outcome(-(x + previousScore - 200));
                    System.out.println("Your excess amount was " + (x + previousScore - 100) + " you have been moved to square " + player2);

                }
                System.out.println("\n");
            }
            if (i == 2) {
                int x = flipDice();
                System.out.println(finalOrder.get(i) + " is on square " + player3 + " and rolled a: " + x);
                player3 = outcome(player3 + x);
                if (player3 == 100) {
                    System.out.println(finalOrder.get(i) + " wins the game!");
                    winCondition = true;
                    System.out.println("\nThank you all for playing. Terminating the program.");
                    System.exit(0);
                } else if (player3 > 100) {
                    int previousScore = player3 - x;
                    player3 = outcome(-(x + previousScore - 200));
                    System.out.println("Your excess amount was " + (x + previousScore - 100) + " you have been moved to square " + player3);

                }
                System.out.println("\n");
            }
            if (i == 3) {
                int x = flipDice();
                System.out.println(finalOrder.get(i) + " is on square " + player3 + " and rolled a: " + x);
                player4 = outcome(player4 + x);
                if (player4 == 100) {
                    System.out.println(finalOrder.get(i) + " wins the game!");
                    winCondition = true;
                    System.out.println("\nThank you all for playing. Terminating the program.");
                    System.exit(0);
                } else if (player4 > 100) {
                    int previousScore = player4 - x;
                    player4 = outcome(-(x + previousScore - 200));
                    System.out.println("Your excess amount was " + (x + previousScore - 100) + " you have been moved to square " + player4);

                }
                System.out.println("\n");
            }


        }
        Scanner userInput = new Scanner(System.in);
        System.out.println("Press enter for the next round");
        userInput.nextLine();

    }

    /**
     * This method will compare the initial landing spot of the player to the standard board in order to evaluate if the
     * player landed on a specific ladder or on a specific snake
     *
     * @return the final square in the event that the player lands on a snake or a ladder, otherwise it will return
     * the original square.
     */
    public int outcome(int originalSquare) {
        switch (originalSquare) {
            case 1 -> {
                System.out.println("Reached a ladder on square 1 and climbed to square 38!");
                return 38;
            }
            case 4 -> {
                System.out.println("Reached a ladder on square 4 and climbed to square 14!");
                return 14;
            }
            case 9 -> {
                System.out.println("Reached a ladder on square 9 and climbed to square 31!");
                return 31;
            }
            case 21 -> {
                System.out.println("Reached a ladder on square 21 and climbed to square 42!");
                return 42;
            }
            case 28 -> {
                System.out.println("Reached a ladder on square 28 and climbed to square 84!");
                return 84;
            }
            case 36 -> {
                System.out.println("Reached a ladder on square 36 and climbed to square 44!");
                return 44;
            }
            case 51 -> {
                System.out.println("Reached a ladder on square 51 and climbed to square 67!");
                return 67;
            }
            case 71 -> {
                System.out.println("Reached a ladder on square 71 and climbed to square 91!");
                return 91;
            }
            case 80 -> {
                System.out.println("Reached a ladder on square 80 and climbed to square 100!");
                return 100;
            }

            case 2, 3, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 17, 18, 19, 20, 22, 23, 24, 25, 26, 27, 29, 30, 31, 32, 33,
                    34, 35, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 49, 50, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61,
                    62, 63, 65, 66, 67, 68, 69, 70, 72, 73, 74, 75, 76, 77, 78, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90,
                    91, 92, 94, 96, 99, 100 -> {
                System.out.println("Landed on square " + originalSquare);
                return originalSquare;
            }
            case 16 -> {
                System.out.println("Swallowed by a snake on square 16 and landed on square 6!");
                return 6;
            }
            case 48 -> {
                System.out.println("Swallowed by a snake on square 48 and landed on square 30!");
                return 30;
            }
            case 64 -> {
                System.out.println("Swallowed by a snake on square 64 and landed on square 60!");
                return 60;
            }
            case 79 -> {
                System.out.println("Swallowed by a snake on square 79 and landed on square 19!");
                return 19;
            }
            case 93 -> {
                System.out.println("Swallowed by a snake on square 93 and landed on square 68!");
                return 68;
            }
            case 95 -> {
                System.out.println("Swallowed by a snake on square 95 and landed on square 24!");
                return 24;
            }
            case 97 -> {
                System.out.println("Swallowed by a snake on square 97 and landed on square 76!");
                return 76;
            }
            case 98 -> {
                System.out.println("Swallowed by a snake on square 98 and landed on square 78!");
                return 78;
            }
            case 101 -> {
                System.out.println("You have exceeded the 100th square. You will be moved back with the excessive amount of your roll");
                return 101;
            }
            case 102 -> {
                System.out.println("You have exceeded the 100th square. You will be moved back with the excessive amount of your roll");
                return 102;
            }
            case 103 -> {
                System.out.println("You have exceeded the 100th square. You will be moved back with the excessive amount of your roll");
                return 103;
            }
            case 104 -> {
                System.out.println("You have exceeded the 100th square. You will be moved back with the excessive amount of you roll");
                return 104;
            }
            case 105 -> {
                System.out.println("You have exceeded the 100th square. You will be moved back with the excessive amount of your roll");
                return 105;
            }
            default -> {
                return -1;
            }

        }

    }
}

