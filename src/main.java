import java.util.*;
import java.util.concurrent.TimeUnit;


public class main {
    public static void main(String[] args) throws InterruptedException {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String[] parts = scanner.nextLine().split(" ");
            // parts[0] board, parts[1] player, parts[2] turn, parts[3] time, parts[4] last move
            Board board = new Board(parts[0]);
            board.moveGenerator();
            TimeUnit.SECONDS.sleep(1);
            System.out.println(board.getRandomMove());
        }
    }
}
