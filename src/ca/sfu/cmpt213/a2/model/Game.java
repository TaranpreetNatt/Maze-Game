package ca.sfu.cmpt213.a2.model;

public class Game {
    private static int RELIC_WIN_CONDITION = 3;

    public static void setNumOfRelics(int num) {
        RELIC_WIN_CONDITION = num;
    }

    public static boolean win() {
        return Player.getObtainedRelics() == RELIC_WIN_CONDITION;
    }

    public static boolean lose() {
        Coordinate playerPosition = Player.getPlayerPosition();
        return Guardian.atGuardian(playerPosition);
    }
}
