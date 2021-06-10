import java.util.*;

public class Transposition {
    public static Hashtable<Integer, TableData> ttable;

    public Transposition() {
        ttable = new Hashtable<>();
    }

    public boolean containsKey(Board node, int depth) {
        return ttable.containsKey(uniqueHashCode(node, depth));
    }

    public void deleteScore(Board node, int score, int depth)  {
        ttable.remove(uniqueHashCode(node, depth));
    }

    public TableData getTableData(Board node, int depth) {
        return ttable.get(uniqueHashCode(node, depth));
    }

    public void insertScore(Board node, int score, int depth) {
        TableData d = new TableData(score, depth);
        ttable.put(uniqueHashCode(node, depth), d);
    }

    public int getSize() {
        return ttable.size();
    }


    public int uniqueHashCode(Board node, int depth) {
        int result = 256;
        int c1 = (int)(node.whites.HI ^ (node.whites.HI >>> 32));
        int c2 = (int)(node.whites.LO ^ (node.whites.LO >>> 32));
        int c3 = (int)(node.blacks.HI ^ (node.blacks.HI >>> 32));
        int c4 = (int)(node.blacks.LO ^ (node.blacks.LO >>> 32));
        return 37 * result + c1 + c2 + c3 + c4 + depth;
    }
}
