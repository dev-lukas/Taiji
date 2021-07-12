import java.util.concurrent.ThreadLocalRandom;

public class EvolutionLearning {

    /* Train our AI on given Board String */
    public void Learn(String board)
    {
        Transposition ttable_white = new Transposition();
        Transposition ttable_black = new Transposition();
        //playBoard color is irrelevant for us
        Board playBoard = new Board(board, "w");
        Board subBoard;
        PVSearch pv;
        int turn = ThreadLocalRandom.current().nextInt(0,2);
        while(true) {
            if(turn == 1) {
                subBoard = new Board(playBoard.toBoardString(), "w");
                pv = new PVSearch(subBoard, ttable_white, true, false);
                if(pv.getBestMove() == null) break;
                playBoard.doMove(pv.getBestMove());
                turn = 0;
                System.out.println("White Move: " + pv.getBestMove().toString());
            } else {
                subBoard = new Board(playBoard.toBoardString(), "b");
                pv = new PVSearch(subBoard, ttable_black, true, false);
                if(pv.getBestMove() == null) break;
                playBoard.doMove(pv.getBestMove());
                turn = 1;
                System.out.println("Black Move: " + pv.getBestMove().toString());
            }
            System.out.println("Current PlayBoard: " + playBoard.toBoardString());
        }
        int score_white = new Board(playBoard.toBoardString(), "w").h();
        int score_black = new Board(playBoard.toBoardString(), "b").h();
        System.out.println("The Game ended with a score of: " + score_white + " (white) / " + score_black + " (black)");
    }

    public static void main(String[] args) {
        EvolutionLearning ev = new EvolutionLearning();
        ev.Learn("-------/-------/-------/-------/-------/-------/-------");
    }
}
