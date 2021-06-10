import org.json.JSONException;
import org.json.JSONObject;

import java.util.Scanner;


public class main {
    public static void main(String[] args) throws InterruptedException, JSONException {
        //Initialize our transposition table that will be used through the game
        Transposition ttable = new Transposition();
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
            // execute pvSearch, the chosen move is in ab.getBestMove()
            PVSearch pv = new PVSearch(board, ttable, false);
            // parse it to String
            String move = board.parseMove(pv.getBestMove());
            //send move to stdout
            System.out.println(move);

        }
    }
}
