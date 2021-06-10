import static java.lang.Math.max;
import static java.lang.Math.min;

public class PVSearch {
    private Zug bestMove;
    private int StateCount = 0;
    public Transposition ttable;

    public PVSearch(Board board, Transposition table, boolean useTTables) {
        //Set transposition table that is to be used
        ttable = table;
        //Time management
        long window = 100000000L;
        long start = System.nanoTime();
        long end = System.nanoTime();

        if(useTTables) {
            for (int distance = 1; distance < Integer.MAX_VALUE && end - start <= window; distance++) {
                pvSearchTable(board, distance, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
                end = System.nanoTime();
            }
        } else {
            for (int distance = 1; distance < Integer.MAX_VALUE && end - start <= window; distance++) {
                pvSearch(board, distance, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
                end = System.nanoTime();
            }

        }
    }

    public int pvSearchTable(Board node,int depth, int alpha, int beta, boolean isRoot) {
        if (depth == 0 || node.getMoves().size() == 0) return node.h();
        int score;
        for (Zug z : node.getMoves()) {
            if (bestMove == null) bestMove = new Zug(z);
            Board child = new Board(node, z);
            StateCount++;
            if(!ttable.containsKey(child) || ttable.getTableData(child).getDepth() < depth - 1)  {
                if(z == node.getMoves().get(0)) {
                    score = pvSearchTable(child, depth - 1, -beta, -alpha, false);
                } else {
                    score = pvSearchTable(child, depth - 1, -alpha - 1, -alpha, false);
                    if (alpha < score && score < beta) {
                        score = pvSearchTable(child, depth - 1, -beta, -score, false);
                    }
                }
                ttable.insertData(child, score, depth - 1);
            } else  {
                score = ttable.getTableData(child).getScore();
            }
            if(alpha < score && isRoot) bestMove = new Zug(z);
            alpha = max(alpha, score);
            if( alpha >= beta ) break;
        }
        return alpha;
    }

    public int pvSearch(Board node,int depth, int alpha, int beta, boolean isRoot) {
        if (depth == 0 || node.getMoves().size() == 0) return node.h();
        int score;
        for (Zug z : node.getMoves()) {
            if (bestMove == null) bestMove = new Zug(z);
            Board child = new Board(node, z);
            StateCount++;
            if (z == node.getMoves().get(0)) {
                score = pvSearch(child, depth - 1, -beta, -alpha, false);
            } else {
                score = pvSearch(child, depth - 1, -alpha - 1, -alpha, false);
                if (alpha < score && score < beta) {
                    score = pvSearch(child, depth - 1, -beta, -score, false);
                }
            }
            if(alpha < score && isRoot) bestMove = new Zug(z);
            alpha = max(alpha, score);
            if( alpha >= beta ) break;
        }
        return alpha;
    }

    public Zug getBestMove(){
        return bestMove;
    }
    public int getStateCount() { return StateCount; }
    public void setStateCount(int i) { StateCount = i; }
}