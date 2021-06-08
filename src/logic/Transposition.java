import java.util.*;

public class Transposition {
    public static Hashtable<BoardHash, Integer> ttable;

    public Transposition() {
        ttable = new Hashtable<>();
    }

    public boolean containsKey(Board node) {
        BoardHash b = new BoardHash(node.whites, node.blacks);
        return ttable.containsKey(b);
    }

    public int getScore(Board node) {
        BoardHash b = new BoardHash(node.whites, node.blacks);
        return ttable.get(b);
    }

    public void insertScore(Board node, int score) {
        BoardHash b = new BoardHash(node.whites, node.blacks);
        ttable.put(b, score);
    }
}
