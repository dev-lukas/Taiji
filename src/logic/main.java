import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;


public class main {
    public static void main(String[] args) throws InterruptedException, JSONException {

        while(true) {
            // get input from stdin and parse it to json object
            Scanner scanner = new Scanner(System.in);
            String json = "";
            //remove {
            for (int i = 0; i < 9; i++) {
                json += scanner.nextLine();
                json += "\n";
            }
            JSONObject obj = new JSONObject(json);


            int timeLeft = obj.getInt("remainingTime");
            String currentBoard = obj.getString("currentBoard");
            String yourColour = obj.getString("yourColour");
            String gameState = obj.getString("gameState");

            // this constructor loads a board and possible moves directly
            Board board = new Board(currentBoard, yourColour);
            // execute alphabeta, the chosen move is in ab.getBestMove()
            AlphaBeta ab = new AlphaBeta(board, true);
            // parse it to String
            String move = board.parseMove(ab.getBestMove());
            //send move to stdout
            System.out.println(move);

        }
    }
}
