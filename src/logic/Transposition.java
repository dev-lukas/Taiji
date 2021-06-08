import java.util.*;

public class Transposition {
    Hashtable<BoardHash, Zug> ttable;

    public Transposition() {
        ttable = new Hashtable<>();
    }

    public Zug checkForMove(Board node) {
        BoardHash b = new BoardHash(node.whites, node.blacks);
        return ttable.get(b);
    }

    public void insertMove(Board node, Zug bestMove) {
        BoardHash b = new BoardHash(node.whites, node.blacks);
        ttable.put(b, bestMove);
    }
}
