import java.util.*;

public class Transposition {
    public static Hashtable<Integer, TableData> ttable;

    public Transposition() {
        ttable = new Hashtable<>();
    }

    public boolean containsKey(Board node) {
        BoardHash b = new BoardHash(node.whites, node.blacks);
        return ttable.containsKey(b.hashCode());
    }

    public TableData getScore(Board node) {
        BoardHash b = new BoardHash(node.whites, node.blacks);
        return ttable.get(b.hashCode());
    }

    public void insertScore(Board node, int score, int depth) {
        BoardHash b = new BoardHash(node.whites, node.blacks);
        TableData d = new TableData(score, depth);
        ttable.put(b.hashCode(), d);
    }

    public int getSize() {
        return ttable.size();
    }
}
