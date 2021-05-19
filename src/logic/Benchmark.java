import static java.lang.Math.max;
import static java.lang.Math.min;

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

    public static void benchmark(String board) {
        System.out.println("---------------------------------------------------");
        System.out.println("Benchmarking: " + board);
        System.out.println("Running Rating Benchmark...");
        System.out.println(benchmarkRating(board, 1000));
        System.out.println("Running Minimax Benchmark...");
        for(int i = 1; i < 3; i++) {
            System.out.println(benchmarkMiniMax(board, i));
        }
        System.out.println("Running AlphaBeta Benchmark...");
        for(int i = 1; i < 3; i++) {
            System.out.println(benchmarkAlphaBeta(board, i));
        }
    }

   public static void main(String[] args) {
        benchmark("-------/-------/-------/-------/-------/-------/-------");
        benchmark("---------/---------/---------/---------/---------/---------/---------/---------/---------");
        benchmark("-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------/-----------");
        benchmark("wbw----/bbw----/-wwb---/-b-bb--/-wbww--/-------/-------");
        benchmark("bbb----/www----/bb-----/-bw----/-bw----/--wb---/-bw----");
        benchmark("---------/---------/---------/--bw-wb--/--bwwwb--/----b--wb/---wwwwwb/---bbbbw-/-------b-");
    }
}
