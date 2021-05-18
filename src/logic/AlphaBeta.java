import static java.lang.Math.max;
import static java.lang.Math.min;

public class AlphaBeta {
    private Zug bestMove;

    // mit iterative tiefensuche
    public AlphaBeta(Board board/*, int timeLeft*/, boolean cutoffs){

        // test: give max 4 sec per turn
        long start = System.nanoTime();
        long end = System.nanoTime();
        // 4 seconds in nanoseconds
        
        long window = 100000000L;
        

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
        }
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

                if (oldValue < value && isRoot) bestMove = new Zug(z);
            }
            return value;
        }
        else {
            int value = Integer.MAX_VALUE;
            for (Zug z : node.getMoves()) {
                // create child board, apply move z and remove it from the move list
                Board child = new Board(node,z);
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
}
