import static java.lang.Math.max;
import static java.lang.Math.min;

public class PVSearch {
    private Zug bestMove;
    private int StateCount = 0;
    public final Transposition ttable;

    public PVSearch(Board board, Transposition table) {
        //Set transposition table that is to be used
        ttable = table;
        //Time management
        long window = 100000000L;
        long start = System.nanoTime();
        long end = System.nanoTime();

        for (int distance = 1; distance < Integer.MAX_VALUE && end - start <= window; distance++) {
            transpositionHandler(board, distance, Integer.MIN_VALUE, Integer.MAX_VALUE,true);
            end = System.nanoTime();
        }
    }

    public int transpositionHandler(Board board, int distance, int alpha, int beta, boolean isRoot) {
        if(!ttable.containsKey(board)) {
            int score = pvSearchTable(board, distance, alpha, beta, isRoot, ttable);
            ttable.insertScore(board, score);
            return score;
        } else {
            return ttable.getScore(board);
        }
    }

    public int pvSearchTable(Board node,int depth, int alpha, int beta, boolean isRoot, Transposition ttable) {
        if (depth == 0 || node.getMoves().size() == 0) return node.h();
        int score;
        for (Zug z : node.getMoves()) {
            if (bestMove == null) bestMove = new Zug(z);
            Board child = new Board(node, z);
            StateCount++;
            if (z == node.getMoves().get(0)) {
                score = transpositionHandler(child, depth - 1, -beta, -alpha, false);
            } else {
                score = transpositionHandler(child, depth - 1, -alpha - 1, -alpha, false);
                if (alpha < score && score < beta) {
                    score = transpositionHandler(child, depth - 1, -beta, -score, false);
                }
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
