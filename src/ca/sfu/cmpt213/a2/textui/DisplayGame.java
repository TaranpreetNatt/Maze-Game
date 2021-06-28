package ca.sfu.cmpt213.a2.textui;

public class DisplayGame {

    public static void main(String[] args) {
        SpecialKeys keys = new SpecialKeys();
        Input input = new Input();
        keys.help();
        char move = input.userMoveInput();


    }
}
