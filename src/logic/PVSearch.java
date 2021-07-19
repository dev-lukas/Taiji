import java.util.ArrayList;

import static java.lang.Math.max;
import static java.lang.Math.min;
/*
    PVSearch
    Implementation of Principal Variation Sort with or without Transposition Tables
 */
public class PVSearch {
    private Zug bestMove;
    private int StateCount = 0;
    public Transposition ttable;
    public long start;
    public long window;
    public boolean benching;

    public PVSearch(Board board, Transposition table, boolean useTTables, boolean benchmarking, Parameters p) {
        //Set transposition table that is to be used
        ttable = table;
        //Time management  now global
        window = 1000000000L;
        start = System.nanoTime();
        benching = benchmarking;
        long end = System.nanoTime();
        //Use either vanilla pvSearch or with transposition tables
        if(useTTables  && !benchmarking) {
            for (int distance = 1; distance < Integer.MAX_VALUE && end - start <= window; distance++) {
                pvSearchTable(board, distance, Integer.MIN_VALUE, Integer.MAX_VALUE, true, p, null);
                end = System.nanoTime();
            }
        } else if(!benchmarking)  {
            for (int distance = 1; distance < Integer.MAX_VALUE && end - start <= window; distance++) {
                pvSearch(board, distance, Integer.MIN_VALUE, Integer.MAX_VALUE, true);
                end = System.nanoTime();
            }
        }
    }

    public int pvSearchTable(Board node,int depth, int alpha, int beta, boolean isRoot, Parameters p, Zug zug) {
        //anchor
        if (depth == 0 || node.getMoves().size() == 0) return node.ratingfunction(p, zug);
        int score;
        for (Zug z : node.getMoves()) {
            //time management -  break if we are over the window
            if (System.nanoTime() - start > window && !benching) break;
            if (bestMove == null) bestMove = new Zug(z);
            Board child = new Board(node, z);
            StateCount++;
            //Check our table if we have an key for the board already. Also check if the depth of entry is appropriate
            if(!ttable.containsKey(child) || ttable.getTableData(child).getDepth() < depth - 1)  {
                if(z == node.getMoves().get(0)) {
                    score = pvSearchTable(child, depth - 1, -beta, -alpha, false, p, z);
                } else {
                    score = pvSearchTable(child, depth - 1, -alpha - 1, -alpha, false, p, z);
                    if (alpha < score && score < beta) {
                        score = pvSearchTable(child, depth - 1, -beta, -score, false, p, z);
                    }
                }
                //Add entry to database
                ttable.insertData(child, score, depth - 1);
            } else  {
                //We have an appropriate entry -> just use it
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
            if (System.nanoTime() - start > window && !benching) break;
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