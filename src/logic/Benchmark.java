import static java.lang.Math.max;
import static java.lang.Math.min;
/*
    Benchmark
    Class used to test various positions throughtly
 */
public class Benchmark {

    private static String benchmarkRating(String board, int count) {
        long sum = 0;
        for(int i = 0; i < count; i++) {
            Board b = new Board(board, "w");
            long start = System.nanoTime();
            b.h();
            long elapsedTime = System.nanoTime() - start;
            sum += elapsedTime;
        }
        return "Rating function on this board took on average: " + (sum / (float) count) * (float) Math.pow(10,-6) + " ms";
    }

    private static String benchmarkMiniMax(String board, int depth) {
        Board b = new Board(board, "w");
        AlphaBeta ab = new AlphaBeta(b, false);
        long start = System.nanoTime();
        ab.setStateCount(0);
        ab.minimax(b, depth, true, true);
        long elapsedTime = System.nanoTime() - start;
        return "Minimax took " + (elapsedTime * (float) Math.pow(10,-6)) + " ms, looked at " + ab.getStateCount() + " states with the best move being: " + ab.getBestMove();
    }

    private static String benchmarkAlphaBeta(String board, int depth) {
        Board b = new Board(board, "w");
        AlphaBeta ab = new AlphaBeta(b, true);
        long start = System.nanoTime();
        ab.setStateCount(0);
        ab.alphabeta(b, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, true,true);
        long elapsedTime = System.nanoTime() - start;
        return "Alphabeta took " + (elapsedTime * (float) Math.pow(10,-6)) + " ms, looked at " + ab.getStateCount() + " states with the best move being: " + ab.getBestMove();
    }

    private static String benchmarkPV(String board, int depth, Transposition ttable) {
        Board b = new Board(board, "w");
        long start = System.nanoTime();
        PVSearch pv = new PVSearch(b, ttable, false,  true);
        pv.pvSearch(b, depth, Integer.MIN_VALUE, Integer.MAX_VALUE,true);
        long elapsedTime = System.nanoTime() - start;
        return "PV Search took " + (elapsedTime * (float) Math.pow(10,-6)) + " ms, looked at " + pv.getStateCount() + " states with the best move being: " + pv.getBestMove();
    }

    private static String benchmarkPVTable(String board, int depth, Transposition ttable) {
        Board b = new Board(board, "w");
        long start = System.nanoTime();
        PVSearch pv = new PVSearch(b, ttable, true, true);
        pv.pvSearchTable(b, depth, Integer.MIN_VALUE, Integer.MAX_VALUE,true);
        long elapsedTime = System.nanoTime() - start;
        return "PV Search took " + (elapsedTime * (float) Math.pow(10,-6)) + " ms, looked at " + pv.getStateCount() + " states with the best move being: " + pv.getBestMove() + " with " + ttable.getSize() + " table entries";
    }

    public static void benchmark(String board, int depth) {
        Transposition ttable = new Transposition();
        System.out.println("---------------------------------------------------");
        System.out.println("Benchmarking: " + board);
        System.out.println("Running Rating Benchmark...");
        System.out.println(benchmarkRating(board, 1000));
        System.out.println("Running PV Search Benchmark...");
        for(int i = 1; i < depth+1; i++) {
            System.out.println(benchmarkPV(board, i, ttable));
        }
        System.out.println("Running PV Table Search Benchmark...");
        for(int i = 1; i < depth+1; i++) {
            System.out.println(benchmarkPVTable(board, i, ttable));
        }
    }

   public static void main(String[] args) {
       benchmark("-------/-------/-------/-------/-------/-------/-------",4);
       benchmark("wbw----/bbw----/-wwb---/-b-bb--/-wbww--/-------/-------",4);
       benchmark("bbb----/www----/bb-----/-bw----/-bw----/--wb---/-bw----",4);
       benchmark("---------/---------/---------/---------/---------/---------/---------/---------/---------",4);
       benchmark("---------/---------/---------/--bw-wb--/--bwwwb--/----b--wb/---wwwwwb/---bbbbw-/-------b-",4);
       benchmark("-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------",4);
       benchmark("wb-b-wbwbwb/---w-w--w-w/--bb-b--b-b/--ww---wb-w/b--wbw----b/w-b--bwb---/--ww-----w-/-wbb-bw--b-/-w-----w-bw/-b-w-w-b---/---b-b---wb",4);
    }
}
