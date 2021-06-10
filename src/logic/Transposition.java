import java.util.*;
/*
    Transposition
    Our Transposition Table Class
    Using a custom boardkey hash as key and tabledata to store our values
 */
public class Transposition {
    public static Hashtable<Long, TableData> ttable;
    long RWHI;
    long RWLO;
    long RBHI;
    long RBLO;

    public Transposition() {
        RWHI = new Random().nextLong();
        RWLO = new Random().nextLong();
        RBHI = new Random().nextLong();
        RBLO = new Random().nextLong();
        ttable = new Hashtable<>();
    }

    public boolean containsKey(Board node) {
        return ttable.containsKey(uniqueHashCode(node));
    }

    public TableData getTableData(Board node) {
        return ttable.get(uniqueHashCode(node));
    }

    public void insertData(Board node, int score, int depth) {
        TableData d = new TableData(score, depth);
        ttable.put(uniqueHashCode(node), d);
    }

    public int getSize() {
        return ttable.size();
    }

    /*
        Hashfunction for unique hashes for our board...seems to work? kinda
     */
    public long uniqueHashCode(Board node) {
        long hash = 17;
        long l1 = (node.whites.HI ^ RWHI);
        long l2 = (node.whites.LO ^ RWLO);
        long l3 = (node.blacks.HI ^ RBHI);
        long l4 = (node.blacks.LO ^ RBLO);
        hash = hash * 31 + l1;
        hash = hash * 31 + l2;
        hash = hash * 31 + l3;
        hash = hash * 31 + l4;
        return hash;
    }
}
