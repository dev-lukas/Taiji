import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.ThreadLocalRandom;

/*
    EvolutionTable contains an ArrayList that holds our 10 different contestants while learning
    Each contestant has his own parameters and game statistics
 */

public class EvolutionTable {
    public static ArrayList<ETableData> etable;
    /*
        Our Evolution Table Data, containing all different players with their parameters and game statistics
     */

    //Generating a new EvolutionTable
    public EvolutionTable() {
        etable = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            int edge = ThreadLocalRandom.current().nextInt(-100, 101);
            int middle = ThreadLocalRandom.current().nextInt(-100, 101);
            int incrementPoints = ThreadLocalRandom.current().nextInt(-100, 101);
            int onAnother = ThreadLocalRandom.current().nextInt(-100, 101);
            Parameters parameters = new Parameters(edge, middle, incrementPoints, onAnother);
            ETableData data = new ETableData(parameters);
            etable.add(data);
        }
    }

    public ETableData getTableData(int i) {
        return etable.get(i);
    }

    //Bubble Sort going after games won, then avg point difference
    public void sortBest() {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9 - i; j++) {
                if(etable.get(j).getGames_won() > etable.get(j+1).getGames_won()) {
                    ETableData buffer = etable.get(j);
                    etable.set(j, etable.get(j+1));
                    etable.set(j+1, buffer);
                } else if(etable.get(j).getGames_won() == etable.get(j+1).getGames_won() && etable.get(j).getTotal_margin() > etable.get(j+1).getTotal_margin()) {
                    ETableData buffer = etable.get(j);
                    etable.set(j, etable.get(j+1));
                    etable.set(j+1, buffer);
                }
            }
        }
    }

    public void setTableData(int i, ETableData data) {
        etable.set(i, data);
    }

    public void resetTableStats() {
        for(int i = 0; i < 10; i++) {
            ETableData data = new ETableData(etable.get(i).getParameters());
            etable.set(i, data);
        }
    }

    public String toString() {
        String table = "";
        for(int i = 0; i < 10; i++) {
            ETableData data = etable.get(i);
            table += i + ": TG: " + data.games_played + " WG: " + data.games_won + " AVG_WMRG: " + data.total_margin / Math.max(data.games_won, 1) + "\n";
            table += "Parameter: " + data.getParameters().getEdge() + " | " + data.getParameters().getMiddle() + " | " + data.getParameters().getIncrementPoints() + " | " + data.getParameters().getOnAnother() + " |\n";
        }
        return table;
    }
}
