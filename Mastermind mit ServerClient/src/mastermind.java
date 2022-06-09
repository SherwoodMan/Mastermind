
import java.awt.Color;

public class Mastermind {

    private static Control con = new Control();
    private static Order toGuess = new Order();
    private static Color[][] guesses = new Color[4][12];
    private static Color[][] reaction = new Color[4][12];
    private static int round;

    public static void main(String[] args) {
        randomOrder(toGuess);
        System.out.println(toGuess.toString());
        greyingArray();
        MainGUI newgame = new MainGUI();
        newgame.initializeStart();
    }
    /*
        > man braucht für statische Klassen keine getters und setters 
    */
    public static int getRound() {
        return round;
    }

    public static void setRound(int round) {
        Mastermind.round = round;
    }

    public static Color[][] getGuesses() {
        return guesses;
    }

    public static void setGuesses(Color[][] guesses) {
        Mastermind.guesses = guesses;
    }

    public static Color[][] getReaction() {
        return reaction;
    }

    public static void setReaction(Color[][] reaction) {
        Mastermind.reaction = reaction;
    }

    

    public static Order randomOrder(Order randomOrder) {
        int zero, one, two, three;

        do {
            zero = (int) (Math.random() * 6);
        } while (con.doubledPins(randomOrder.getOrderArray(), zero));
        randomOrder.getOrderArray()[0] = zero;
        do {
            one = (int) (Math.random() * 6);
        } while (con.doubledPins(randomOrder.getOrderArray(), one));
        randomOrder.getOrderArray()[1] = one;
        do {
            two = (int) (Math.random() * 6);
        } while (con.doubledPins(randomOrder.getOrderArray(), two));
        randomOrder.getOrderArray()[2] = two;
        do {
            three = (int) (Math.random() * 6);
        } while (con.doubledPins(randomOrder.getOrderArray(), three));
        randomOrder.getOrderArray()[3] = three;

        return randomOrder;
    }

    private static void greyingArray() {
        for (int i = 0; i < guesses.length; i++) {
            for (int j = 0; j < guesses[1].length; j++) {
                if (guesses[i][j] == null) {
                    guesses[i][j] = Color.GRAY;
                }
            }
        }
        for (int i = 0; i < reaction.length; i++) {
            for (int j = 0; j < reaction[1].length; j++) {
                if (reaction[i][j] == null) {
                    reaction[i][j] = Color.GRAY;
                }
            }
        }
    }

    public static Order getToGuess() {
        return toGuess;
    }

    public static Control getCon() {
        return con;
    }
}