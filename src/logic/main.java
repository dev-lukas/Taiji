package logic;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class main {
    public static void main(String[] args) throws InterruptedException {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            String[] parts = scanner.nextLine().split(" ");
            // parts[0] board, parts[1] player, parts[2] turn, parts[3] time, parts[4] last move
            Board board = new Board(parts[0],parts[1],parts[3]);
            List<Zug> moves = board.moveGenerator();
            TimeUnit.MILLISECONDS.sleep(50);
            System.out.println(board.getRandomMove());
        }
    }
}
