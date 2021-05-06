import logic.Board;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;



class BoardTest {
    private static float testMoveGenerator(String board, int count) {
        long sum = 0;
        for(int i = 0; i < count; i++) {
            Board b = new Board(board,"w","100");
            long start = System.nanoTime();
            b.moveGenerator();
            long elapsedTime = System.currentTimeMillis() - start;
            sum += elapsedTime;
        }
        return sum * (float) Math.pow(10,-6);
    }


    @Test
    void moveGenerator() {
        String testBoard = "wb-bw-w/--wb-wb/wb--wb-/-wb-b-w/bw-wbwb/bwb--b-/w----bw";
        Board board = new Board(testBoard,"w","100");
        String testBoard1 = "wb-bw-w/-----wb/wb--wb-/----b--/---wb--/b------/w----bw";
        Board board1 = new Board(testBoard1,"w","100");
        assertEquals(18, board.moveGenerator().size());
        assertEquals(78, board1.moveGenerator().size());

        String testBoard2 = "w--w--w-w/b--b--b-b/-----w---/-----b---/-wb-w--wb/----b----/---------/w-wb-w--w/b----b--b";
        Board board2 = new Board(testBoard2,"w","100");
        String testBoard3 = "wb-------bw/-----wb----/--------w--/-wb-w-w-b-w/----b-b---b/--w------w-/--b------b-/-bw--wb-w--/--------b--/-----w-----/wb---b---bw";
        Board board3 = new Board(testBoard3,"w","100");
        assertEquals(152, board2.moveGenerator().size());
        assertEquals(248, board3.moveGenerator().size());
    }


    @Test
    void toStringTest() {
        String testBoard = "---wbbw/-----b-/-----w-/b------/wb-----/ww-----/bwb----";
        Board board = new Board(testBoard,"w","100");
        System.out.println(board.toString());


    }
}