import javax.swing.plaf.nimbus.State;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class AlphaBeta {
    private Zug bestMove;
    private int StateCount = 0;

    // mit iterative tiefensuche
    public AlphaBeta(Board board/*, int timeLeft*/, boolean cutoffs){

        // test: give max 4 sec per turn
        long start = System.nanoTime();
        long end = System.nanoTime();
        // 4 seconds in nanoseconds
        
        long window = 100000000L;

        /* TODO: If pvSearch works redo
        if (cutoffs) {
            for (int distance = 1; distance < Integer.MAX_VALUE && end - start <= window; distance++) {
                alphabeta(board, distance, Integer.MIN_VALUE, Integer.MAX_VALUE, true,true);
                end = System.nanoTime();
            }
        } else {
            for (int distance = 1; distance < Integer.MAX_VALUE && end - start <= window; distance++) {
                minimax(board, distance, true,true);
                end = System.nanoTime();
            }
        }*/
    }

    public void transpositionHandler() {

    }

    public int pvSearch(Board node,int depth, int alpha, int beta, int color, boolean isRoot) {
        if (depth == 0 || node.getMoves().size() == 0) return node.h() * color;
        int score;
        for (Zug z : node.getMoves()) {
            if (bestMove == null) bestMove = new Zug(z);
            Board child = new Board(node, z);
            StateCount++;
            if (z == node.getMoves().get(0)) {
                score = -pvSearch(child, depth - 1, -beta, -alpha, -color, false);
            } else {
                score = -pvSearch(child, depth - 1, -alpha - 1, -alpha, -color, false);
                if (alpha < score && score < beta) {
                    score = -pvSearch(child, depth - 1, -beta, -score, -color, false);
                }
            }
            if(alpha < score && isRoot) bestMove = new Zug(z);
            alpha = max(alpha, score);
            if( alpha >= beta ) break;
        }
        return alpha;
    }

    public int minimax(Board node, int depth, boolean maximizingPlayer, boolean isRoot) {
        if (depth == 0 || node.getMoves().size() == 0) return node.h();

        if (maximizingPlayer) {
            int value = Integer.MIN_VALUE;
            for (Zug z : node.getMoves()){
                if (bestMove == null) bestMove = new Zug(z);
                // create child board, apply move z and remove it from the move list
                Board child = new Board(node,z);

                int oldValue = value;
                value = max(value, minimax(child, depth - 1, false,false));
                StateCount++;
                if (oldValue < value && isRoot) bestMove = new Zug(z);
            }
            return value;
        }
        else {
            int value = Integer.MAX_VALUE;
            for (Zug z : node.getMoves()) {
                // create child board, apply move z and remove it from the move list
                Board child = new Board(node,z);
                StateCount++;
                value = min(value, minimax(child, depth - 1, true,false));
            }
            return value;
        }
    }

    // simple alphabeta
    public int alphabeta(Board node, int depth, int alpha, int beta, boolean maximizingPlayer, boolean isRoot){
        if (depth == 0 || node.getMoves().size() == 0) return node.h();

        if (maximizingPlayer) {
            int value = Integer.MIN_VALUE;
            for (Zug z : node.getMoves()){
                if (bestMove == null) bestMove = new Zug(z);
                // create child board, apply move z and remove it from the move list
                Board child = new Board(node,z);

                int oldValue = value;
                value = max(value, alphabeta(child, depth - 1, alpha, beta, false,false));
                StateCount++;
                // beta cutoff
                alpha = max(alpha, value);

                // set better move
                if (oldValue < value && isRoot) bestMove = new Zug(z);

                if (alpha >= beta) break;
            }
            return value;
        }
        else {
            int value = Integer.MAX_VALUE;
            for (Zug z : node.getMoves()) {
                // create child board, apply move z and generate moves
                Board child = new Board(node,z);

                value = min(value, alphabeta(child, depth - 1, alpha, beta, true,false));
                StateCount++;
                // alpha cutoff
                beta = min(beta, value);
                if (alpha >= beta) break;
            }
            return value;
        }
    }

    public Zug getBestMove(){
        return bestMove;
    }

    public int getStateCount() { return StateCount; }

    public void setStateCount(int i) { StateCount = i; }
}
