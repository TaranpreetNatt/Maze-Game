package ca.sfu.cmpt213.a2.textui;

import java.util.Scanner;

public class Input {
    public char userMoveInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter move: ");
        char move = scanner.next().charAt(0);

        while (!validateMoveInput(move)) {
            System.out.println("Please enter a valid move: ");
            move = scanner.next().charAt(0);
        }
        return move;
    }

    private boolean validateMoveInput(char move) {
        if (move == 'W' || move == 'w' || move == 'A' || move == 'a') {
            return true;
        }
        if (move == 'S' || move == 's' || move == 'D' || move == 'd') {
            return true;
        }

        if (move == '?' || move == 'm' || move == 'M' || move == 'c' || move == 'C') {
            return true;
        }

        return false;
    }
}
