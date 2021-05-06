package logic;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class AlphaBeta {
    public Zug bestMove;

    // mit iterative tiefensuche
    public AlphaBeta(Board board, boolean cutoffs){
        // TODO needs time management
        if (cutoffs) {
            for (int distance = 1; distance < 20 /*Integer.MAX_VALUE && !outOfTime()*/; distance++) {
                    alphabeta(board, distance, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
            }
        } else {
            for (int distance = 1; distance < 20 /*Integer.MAX_VALUE && !outOfTime()*/; distance++) {
                minimax(board, distance, true);
            }
        }
    }


    public int minimax(Board node, int depth, boolean maximizingPlayer) {
        if (depth == 0 || node.getMoves().size() == 0) return node.h();

        if (maximizingPlayer) {
            int value = Integer.MIN_VALUE;
            for (Zug z : node.getMoves()){
                if (bestMove == null) bestMove = new Zug(z);
                // create child board, apply move z and remove it from the move list
                Board child = new Board(node);
                child.doMove(z);
                child.removeMove(z);

                int oldValue = value;
                value = max(value, minimax(child, depth - 1, false));

                if (oldValue < value) bestMove = new Zug(z);
            }
            return value;
        }
        else {
            int value = Integer.MAX_VALUE;
            for (Zug z : node.getMoves()) {
                // create child board, apply move z and remove it from the move list
                Board child = new Board(node);
                child.doMove(z);
                child.removeMove(z);

                value = min(value, minimax(child, depth - 1, true));
            }
            return value;
        }
    }

    // simple alphabeta
    public int alphabeta(Board node, int depth, int alpha, int beta, boolean maximizingPlayer){
        if (depth == 0 || node.getMoves().size() == 0) return node.h();

        if (maximizingPlayer) {
            int value = Integer.MIN_VALUE;
            for (Zug z : node.getMoves()){
                if (bestMove == null) bestMove = new Zug(z);
                // create child board, apply move z and remove it from the move list
                Board child = new Board(node);
                child.doMove(z);
                child.removeMove(z);

                int oldValue = value;
                value = max(value, alphabeta(child, depth - 1, alpha, beta, false));

                // beta cutoff
                alpha = max(alpha, value);

                // set better move
                if (oldValue < value) bestMove = new Zug(z);


                if (alpha >= beta) break;
            }
            return value;
        }
        else {
            int value = Integer.MAX_VALUE;
            for (Zug z : node.getMoves()) {
                // create child board, apply move z and remove it from the move list
                Board child = new Board(node);
                child.doMove(z);
                child.removeMove(z);

                value = min(value, alphabeta(child, depth - 1, alpha, beta, true));

                // alpha cutoff
                beta = min(beta, value);
                if (alpha >= beta) break;
            }
            return value;
        }
    }
}
