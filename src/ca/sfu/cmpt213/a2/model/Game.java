package ca.sfu.cmpt213.a2.model;

public class Game {
    private static int RELIC_WIN_CONDITION = 3;

    public static void setNumOfRelics(int num) {
        RELIC_WIN_CONDITION = num;
    }

    public static boolean win() {
        if (Player.getObtainedRelics() == RELIC_WIN_CONDITION) {
            return true;
        }
        return false;
    }
}
