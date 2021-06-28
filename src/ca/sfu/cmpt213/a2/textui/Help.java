package ca.sfu.cmpt213.a2.textui;

import ca.sfu.cmpt213.a2.model.Game;
import ca.sfu.cmpt213.a2.model.Player;

/** Class to dispaly directions, legend, movement instructions, and obtained relic and relic win condition.
 * @author Taranpreet
 */
public class Help {

    private void displayDirections() {
        System.out.println();
        System.out.print("Directions: ");
        System.out.println("Collect 3 relics!");
    }

    private void displayLegend() {
        System.out.println("Legend:");
        System.out.println("#: Wall");
        System.out.println("@: You (The Treasure hunter)");
        System.out.println("!: Guardian");
        System.out.println("^: Relic");
        System.out.println(".: Unexplored Space");
    }

    public void displayMoveInstructions() {
        System.out.println("Use W (up), A (left), S (down), D (right)");
        System.out.println("Only one character is allowed at a time");
        System.out.println("You must press enter after every move");
    }

    public void displayRelicCount() {
        int obtainedRelics = Player.getObtainedRelics();
        int relicsToBeCollected = Game.getRelicWinCondition();
        System.out.print("Total number of relics to be collected: ");
        System.out.println(relicsToBeCollected);
        System.out.print("Numer of relics currently in possession: ");
        System.out.println(obtainedRelics);
    }

    public void help() {
        displayDirections();
        System.out.println();
        displayLegend();
        System.out.println();
        displayMoveInstructions();
        System.out.println();
    }
}
