
import logic.AlphaBeta;
import logic.Board;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class AlphaBetaTest {

    @Test
    void minimax() {

        String testBoard = "-------/-------/-------/-------/-------/-------/-------";
        Board board = new Board(testBoard,"w");
        AlphaBeta a = new AlphaBeta(board, false);
        assertEquals(0, a.minimax(board, 1, false, false));
        assertEquals(1, a.minimax(board, 2, false, false));

        String testBoard1 = "---------/---------/---------/---------/---------/---------/---------/---------/---------";
        Board board1 = new Board(testBoard1,"b");
        AlphaBeta a1 = new AlphaBeta(board1, false);
        assertEquals(0, a1.minimax(board, 1, false, false));
        assertEquals(1, a1.minimax(board, 2, false, false));

        String testBoard2 = "-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------";
        Board board2 = new Board(testBoard2,"w");
        AlphaBeta a2 = new AlphaBeta(board2, false);
        assertEquals(0, a2.minimax(board, 1, false, false));
        assertEquals(1, a2.minimax(board, 2, false, false));

        String testBoard3 = "wbw----/bbw----/-wwb---/-b-bb--/-wbww--/-------/-------";
        Board board3 = new Board(testBoard3,"b");
        AlphaBeta a3 = new AlphaBeta(board3, false);
        assertEquals(0, a3.minimax(board, 1, false, false));
        assertEquals(1, a3.minimax(board, 2, false, false));

        String testBoard4 = "bbb----/www----/bb-----/-bw----/-bw----/--wb---/-bw----";
        Board board4 = new Board(testBoard4,"w");
        AlphaBeta a4 = new AlphaBeta(board4, false);
        assertEquals(0, a4.minimax(board, 1, false, false));
        assertEquals(1, a4.minimax(board, 2, false, false));

        String testBoard5 = "---------/---------/---------/--bw-wb--/--bwwwb--/----b--wb/---wwwwwb/---bbbbw-/-------b-";
        Board board5 = new Board(testBoard5,"b");
        AlphaBeta a5 = new AlphaBeta(board5, false);
        assertEquals(0, a5.minimax(board, 1, false, true));
        assertEquals(1, a5.minimax(board, 2, false, true));

    }

    @Test
    void alphabeta() {
        String testBoard = "-------/-------/-------/-------/-------/-------/-------";
        Board board = new Board(testBoard,"w");
        AlphaBeta a = new AlphaBeta(board, true);
        assertEquals(0, a.alphabeta(board, 1, 0,0,false, false));
        assertEquals(0, a.alphabeta(board, 2,0,0, false, false));

        String testBoard1 = "---------/---------/---------/---------/---------/---------/---------/---------/---------";
        Board board1 = new Board(testBoard1,"b");
        AlphaBeta a1 = new AlphaBeta(board1, true);
        assertEquals(0, a1.alphabeta(board, 1, 0,0,false, false));
        assertEquals(0, a1.alphabeta(board, 2,0,0, false, false));

        String testBoard2 = "-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------";
        Board board2 = new Board(testBoard2,"w");
        AlphaBeta a2 = new AlphaBeta(board2, true);
        assertEquals(0, a2.alphabeta(board, 1,10,-10, false, false));
        assertEquals(0, a2.alphabeta(board, 2, 0,0,false, false));

        String testBoard3 = "wbw----/bbw----/-wwb---/-b-bb--/-wbww--/-------/-------";
        Board board3 = new Board(testBoard3,"b");
        AlphaBeta a3 = new AlphaBeta(board3, true);
        assertEquals(0, a3.alphabeta(board, 1,100,-100, false, false));
        assertEquals(0, a3.alphabeta(board, 2,0,0, false, false));

        String testBoard4 = "bbb----/www----/bb-----/-bw----/-bw----/--wb---/-bw----";
        Board board4 = new Board(testBoard4,"w");
        AlphaBeta a4 = new AlphaBeta(board4, true);
        assertEquals(0, a4.alphabeta(board, 1,-100,100, false, false));
        assertEquals(0, a4.alphabeta(board, 2, 0,0,false, false));

        String testBoard5 = "---------/---------/---------/--bw-wb--/--bwwwb--/----b--wb/---wwwwwb/---bbbbw-/-------b-";
        Board board5 = new Board(testBoard5,"b");
        AlphaBeta a5 = new AlphaBeta(board5, true);
        assertEquals(0, a5.alphabeta(board, 1, 0,0,false, true));
        assertEquals(0, a5.alphabeta(board, 2,1,0, false, true));

    }
}