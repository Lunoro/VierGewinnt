package de.lenny.viergewinnt.game;

import java.util.Random;
import java.util.Scanner;

public class Game {

    private boolean running = true;
    private char[][] field;

    private Player[] players = new Player[2];
    private final int size;

    public Game(int size) {
        this.size = size;

        fieldInit(size, size);
    }

    private void fieldInit(int x, int y) {
        field = new char[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                field[i][j] = '\0';
            }
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        initializePlayer(scanner);

        boolean turnPlayerOne = new Random().nextBoolean();

        render();

        while (this.running) {
            if (players[0] == null || players[1] == null) {
                System.out.println("No players found");
                return;
            }

            Player turnPlayer = turnPlayerOne ? players[0] : players[1];
            System.out.println("Turn of " + turnPlayer.getName());

            int inputLocation = Integer.parseInt(scanner.next());

            place(turnPlayer, inputLocation);
            System.out.println("--------------");
            render();

            if (wonHorizontal(turnPlayer) || wonVertical(turnPlayer)) {
                System.out.println(turnPlayer.getName() + " won!");
                break;
            }

            turnPlayerOne = !turnPlayerOne;
            /*
            2 Players
            3 Winconditions
            4Spielloop / Regeln
             */

        }
    }

    private void initializePlayer(Scanner scanner) {
        System.out.println("Welcome to the game!");
        System.out.println("Enter name for player one!");
        String namePlayerOne = scanner.next();
        System.out.println("Enter mark for player one!");
        char markOne = scanner.next().charAt(0);
        players[0] = new Player(namePlayerOne, markOne);
        System.out.println("Enter name for player two!");
        String namePlayerTwo = scanner.next();
        System.out.println("Enter mark for player two!");
        char markTwo = scanner.next().charAt(0);
        players[1] = new Player(namePlayerTwo, markTwo);
    }

    private void render() {
        //row
        for (int i = 0; i < size; i++) {
            //collum
            for (int j = 0; j < size; j++) {
                System.out.print("|" + field[i][j] + "|");
            }
            System.out.println("");
        }
    }

    record Location(int x, int y){};

    private Location place(Player player, int locationX) {
        if (locationX > size) {
            System.out.println("Invalid location");
            return null;
        }

        for (int i = size - 1; i >= 0; i--) {
            if (!(field[locationX][i] == '\0')) {
                continue;
            }
            field[locationX][i] = player.getMark();
            return new Location(locationX, i);
        }
        return null;
    }

    private boolean wonVertical(Player player) {
        int buffer = 0;

        //Vertical Check
        for (int i = 0; i < size; i++) {
            buffer = 0;
            for (int j = 0; j < size; j++) {
                if (field[i][j] == player.getMark()) {
                    buffer++;
                }
            }

            if (buffer == size) {
                return true;
            }
        }
        return false;
    }

    private boolean wonHorizontal(Player player) {
        int buffer = 0;

        //Horizontal Check
        for (int i = 0; i < size; i++) {
            buffer = 0;
            for (int j = 0; j < size; j++) {
                if (field[j][i] == player.getMark()) {
                    buffer++;
                }
            }
            if (buffer == size) {
                return true;
            }
        }
        return false;
    }

    //TODO: überleg nochmal
    private boolean wonCross(Player player, Location location) {
        int buffer = 0;

        for (int i = location.x; i < size; i++) {

        }

        for (int i = location.x; i < size; i--) {

        }

        return false;
    }
}