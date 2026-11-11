package de.lenny.viergewinnt.game;

import java.util.Scanner;

public class Game {

    private boolean running = true;
    private char[][] field;

    private Player[] players = new Player[2];
    private Player turnPlayer;

    private final int x;
    private final int y;

    public Game(int x, int y) {
        this.x = x;
        this.y = y;

        fieldInit(x, y);
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
        while (this.running) {
            if(players[0] == null || players[1] == null) {
                System.out.println("No players found");
                return;
            }


            int input = Integer.parseInt(scanner.next());
            //bestimme Spieler

            place(turnPlayer, input);

            System.out.println("--------------");
            render();

            /*
            2 Players
            3 Winconditions
            4Spielloop / Regeln
             */

        }
    }

    public void render() {
        //row
        for (int i = 0; i < x; i++) {
            //collum
            for (int j = 0; j < y; j++) {
                System.out.print("|" + field[i][j] + "|");
            }
            System.out.println("");
        }
    }

    public boolean place(Player player, int locationX) {
        if(locationX > x) {
            System.out.println("Invalid location");
            return false;
        }

        for (int i = y - 1; i >= 0; i--) {
            if (!(field[locationX][i] == '\0')) {
                continue;
            }
            field[locationX][i] = 'O';
            return true;
        }
        return false;
    }
}
