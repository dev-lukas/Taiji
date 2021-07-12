import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class EvolutionLearning {

    private Logger logger;

    public EvolutionLearning(Logger logger) {
        this.logger = logger;
    }

    /* Train our AI on given Board String */
    public void Learn(String board, int generations) {
        //Create new EvolutionTable with 10 random configs
        EvolutionTable table = new EvolutionTable();
        //For each Generation
        for(int g = 1; g <= generations; g++) {
            System.out.println("Starting Generation " + g + " Tournament");
            //Let them play against each other in a swiss tournament and update values
            for (int i = 0; i < 4; i++) {
                for(int j = 0; j < 10; j = j + 2) {
                    ETableData player_1_data = table.getTableData(j);
                    ETableData player_2_data = table.getTableData(j+1);
                    int[] score = Play(board, player_1_data.getParameters(), player_2_data.getParameters());
                    System.out.println("Round " + (i+1) + " | Match " + (j+2)/2 + " with Score " + score[0] + " / " + score[1]);
                    if(score[0] == 0) {
                        table.setTableData(j, new ETableData(
                                player_1_data.getParameters(),
                                player_1_data.getGames_won(),
                                player_1_data.getGames_played() + 1,
                                player_1_data.getTotal_margin()));
                        table.setTableData(j+1, new ETableData(
                                player_2_data.getParameters(),
                                player_2_data.getGames_won(),
                                player_2_data.getGames_played() + 1,
                                player_2_data.getTotal_margin()));
                    } else if(score[0] > 0) {
                        table.setTableData(j, new ETableData(
                                player_1_data.getParameters(),
                                player_1_data.getGames_won() + 1,
                                player_1_data.getGames_played() + 1,
                                player_1_data.getTotal_margin() + score[0]));
                        table.setTableData(j+1, new ETableData(
                                player_2_data.getParameters(),
                                player_2_data.getGames_won(),
                                player_2_data.getGames_played() + 1,
                                player_2_data.getTotal_margin()));
                    } else {
                        table.setTableData(j, new ETableData(
                                player_1_data.getParameters(),
                                player_1_data.getGames_won(),
                                player_1_data.getGames_played() + 1,
                                player_1_data.getTotal_margin()));
                        table.setTableData(j+1, new ETableData(
                                player_2_data.getParameters(),
                                player_2_data.getGames_won() + 1,
                                player_2_data.getGames_played() + 1,
                                player_2_data.getTotal_margin() + score[1]));
                    }
                }
                //Sort Table, best are last
                table.sortBest();
            }
            // Time to crossover - then rinse and repeat
            System.out.println("Swiss Tournament finished, results are:");
            System.out.println(table);
            logger.info("Generation " + g + " finished. Results: \n");
            logger.info(table.toString());
            CrossOverMutate(table);
            // Reset Match Stats for fair competition in next round
            table.resetTableStats();
        }
    }
    /*

        Crossover and perhaps mutate the worst 4 of the table

     */
    public void CrossOverMutate(EvolutionTable table) {
        int edge_child_1;
        int edge_child_2;
        int middle_child_1;
        int middle_child_2;
        int incrementPoints_child_1;
        int incrementPoints_child_2;
        int onAnother_child_1;
        int onAnother_child_2;
        for(int i = 0; i < 4; i = i+2) {
            //One-Point crossover at random - we take the best 4 to make the new 4
            switch(ThreadLocalRandom.current().nextInt(0,3)) {
                case 0:
                    edge_child_1 = table.getTableData(i+6).getParameters().getEdge();
                    edge_child_2 = table.getTableData(i+7).getParameters().getEdge();
                    middle_child_1 = table.getTableData(i+7).getParameters().getMiddle();
                    middle_child_2 = table.getTableData(i+6).getParameters().getMiddle();
                    incrementPoints_child_1 = table.getTableData(i+7).getParameters().getMiddle();
                    incrementPoints_child_2 = table.getTableData(i+6).getParameters().getMiddle();
                    onAnother_child_1 = table.getTableData(i+7).getParameters().getOnAnother();
                    onAnother_child_2 = table.getTableData(i+6).getParameters().getOnAnother();
                    break;
                case 1:
                    edge_child_1 = table.getTableData(i+6).getParameters().getEdge();
                    edge_child_2 = table.getTableData(i+7).getParameters().getEdge();
                    middle_child_1 = table.getTableData(i+6).getParameters().getMiddle();
                    middle_child_2 = table.getTableData(i+7).getParameters().getMiddle();
                    incrementPoints_child_1 = table.getTableData(i+7).getParameters().getMiddle();
                    incrementPoints_child_2 = table.getTableData(i+6).getParameters().getMiddle();
                    onAnother_child_1 = table.getTableData(i+7).getParameters().getOnAnother();
                    onAnother_child_2 = table.getTableData(i+6).getParameters().getOnAnother();
                    break;
                default:
                    edge_child_1 = table.getTableData(i+6).getParameters().getEdge();
                    edge_child_2 = table.getTableData(i+7).getParameters().getEdge();
                    middle_child_1 = table.getTableData(i+6).getParameters().getMiddle();
                    middle_child_2 = table.getTableData(i+7).getParameters().getMiddle();
                    incrementPoints_child_1 = table.getTableData(i+6).getParameters().getMiddle();
                    incrementPoints_child_2 = table.getTableData(i+7).getParameters().getMiddle();
                    onAnother_child_1 = table.getTableData(i+7).getParameters().getOnAnother();
                    onAnother_child_2 = table.getTableData(i+6).getParameters().getOnAnother();
                    break;
            }
            //Check for mutate chance
            if(ThreadLocalRandom.current().nextInt(0,1001) == 420) {
                int mutateFactor = ThreadLocalRandom.current().nextInt(-20, 21); //Random Mutate factor
                edge_child_1 += mutateFactor;
                edge_child_2 += mutateFactor;
                middle_child_1 += mutateFactor;
                middle_child_2 += mutateFactor;
                incrementPoints_child_1 += mutateFactor;
                incrementPoints_child_2 += mutateFactor;
                onAnother_child_1 += mutateFactor;
                onAnother_child_2 += mutateFactor;
            }
            //Replace them
            table.setTableData(i, new ETableData(new Parameters(edge_child_1, middle_child_1, incrementPoints_child_1, onAnother_child_1)));
            table.setTableData(i+1, new ETableData(new Parameters(edge_child_2, middle_child_2, incrementPoints_child_2, onAnother_child_2)));
        }
    }

    /* Play a 1v1 on a board string, given two parameters */
    public int[] Play(String board, Parameters player_1, Parameters player_2)
    {
        Transposition ttable_white = new Transposition();
        Transposition ttable_black = new Transposition();
        //playBoard color is irrelevant for us
        Board playBoard = new Board(board, "w");
        Board subBoard;
        PVSearch pv;
        //Randomize who gets which color
        int player_1_turn = ThreadLocalRandom.current().nextInt(0, 2);
        int[] result = new int[2];
        while(true) {
            if(player_1_turn == 0) {
                subBoard = new Board(playBoard.toBoardString(), "w");
                pv = new PVSearch(subBoard, ttable_white, true, false);
                if(pv.getBestMove() == null) break;
                playBoard.doMove(pv.getBestMove());
                subBoard = new Board(playBoard.toBoardString(), "b");
                pv = new PVSearch(subBoard, ttable_black, true, false);
                if(pv.getBestMove() == null) break;
                playBoard.doMove(pv.getBestMove());
            } else {
                subBoard = new Board(playBoard.toBoardString(), "w");
                pv = new PVSearch(subBoard, ttable_white, true, false);
                if(pv.getBestMove() == null) break;
                playBoard.doMove(pv.getBestMove());
                subBoard = new Board(playBoard.toBoardString(), "b");
                pv = new PVSearch(subBoard, ttable_black, true, false);
                if(pv.getBestMove() == null) break;
                playBoard.doMove(pv.getBestMove());
            }

        }
        if(player_1_turn == 0) {
            result[0] = new Board(playBoard.toBoardString(), "w").h();
            result[1] = new Board(playBoard.toBoardString(), "b").h();
        } else {
            result[0] = new Board(playBoard.toBoardString(), "b").h();
            result[1] = new Board(playBoard.toBoardString(), "w").h();
        }
        return result;
    }

    public static void main(String[] args) {
        Logger loggerr = Logger.getLogger("LearningLog");
        FileHandler fh;
        try {
            fh = new FileHandler("C:\\Users\\lbrot\\Desktop\\KI_Log\\LearningLog.log");
            loggerr.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        EvolutionLearning ev = new EvolutionLearning(loggerr);
        ev.Learn("-------/-------/-------/-------/-------/-------/-------", 4);
    }
}
