import logic.Board;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


class BoardTest {
    private static float testMoveGenerator(String board, int count) {
        long sum = 0;
        for(int i = 0; i < count; i++) {
            Board b = new Board(board,"w");
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
        Board board = new Board(testBoard,"w");
        String testBoard1 = "wb-bw-w/-----wb/wb--wb-/----b--/---wb--/b------/w----bw";
        Board board1 = new Board(testBoard1,"w");
        System.out.println(board.getMoves());
        assertEquals(18, board.getMoves().size());
        assertEquals(78, board1.getMoves().size());

        String testBoard2 = "w--w--w-w/b--b--b-b/-----w---/-----b---/-wb-w--wb/----b----/---------/w-wb-w--w/b----b--b";
        Board board2 = new Board(testBoard2,"w");
        String testBoard3 = "wb-------bw/-----wb----/--------w--/-wb-w-w-b-w/----b-b---b/--w------w-/--b------b-/-bw--wb-w--/--------b--/-----w-----/wb---b---bw";
        Board board3 = new Board(testBoard3,"w");
        assertEquals(152, board2.getMoves().size());
        assertEquals(248, board3.getMoves().size());
    }


    @Test
    void h() {
        //7x7
        String testBoard = "wb-bw-w/--wb-wb/wb--wb-/-wb-b-w/bw-wbwb/bwb--b-/w----bw";
        Board board = new Board(testBoard, "w");
        String testBoard1 = "wb-bw-w/-----wb/wb--wb-/----b--/---wb--/b------/w----bw";
        Board board1 = new Board(testBoard1, "w");
        assertEquals(1, board.h());
        assertEquals(-1, board1.h());

        //9x9
        String testBoard2 = "w--w--w-w/b--b--b-b/-----w---/-----b---/-wb-w--wb/----b----/---------/w-wb-w--w/b----b--b";
        Board board2 = new Board(testBoard2, "w");
        String testBoard3 = "bw-wbwbw-/-b-b-bwbb/-w---w-ww/-bwwbb-bw/bb---bw--/wwbw--bbw/bb-b--ww-/wwwwbw-b-/--b--bw--";
        Board board3 = new Board(testBoard3, "w");
        assertEquals(0, board2.h());
        assertEquals(1, board3.h());

        //11x11
        String testBoard4 = "wb-------bw/-----wb----/--------w--/-wb-w-w-b-w/----b-b---b/--w------w-/--b------b-/-bw--wb-w--/--------b--/-----w-----/wb---b---bw";
        Board board4 = new Board(testBoard4, "w");
        String testBoard5 = "bw--bw-bwb-/-b-bw-bwwbw/-w-w--w---b/wbwb-bw--bw/b-bbw-bbww-/--w-w-w--b-/wb--b-b-wbw/b-wbw-wbw-b/w-b-b---b-w/-bwbw-wbw-b/-w-wb-bwbw-";
        Board board5 = new Board(testBoard5, "w");
        assertEquals(0, board4.h());
        assertEquals(0, board5.h());

        //leer
        String leer = "-------/-------/-------/-------/-------/-------/-------";
        Board empty = new Board(leer,"b");
        String leer1 = "---------/---------/---------/---------/---------/---------/---------/---------/---------";
        Board empty1 = new Board(leer1,"b");
        String leer2 = "-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------";
        Board empty2 = new Board(leer2,"b");
        assertEquals(0, empty.h());
        assertEquals(0, empty1.h());
        assertEquals(0, empty2.h());



    }

    @Test
    void toStringTest() {
        String testBoard = "---wbbw/-----b-/-----w-/b------/wb-----/ww-----/bwb----";
        Board board = new Board(testBoard,"w");
        System.out.println(board.toString());


    }
}