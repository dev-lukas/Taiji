public class ETableData {
    /*
        ETableData is a Data wrapper to store the Parameters and match informations in the Evolution Table
     */
    public Parameters parameters;
    public int games_won;
    public int games_played;
    public int total_margin;

    public ETableData(Parameters parameters) {
        this.parameters = parameters;
        this.games_won = 0;
        this.games_played = 0;
        this.total_margin = 0;
    }

    public ETableData(Parameters parameters, int games_won, int games_played, int total_margin) {
        this.parameters = parameters;
        this.games_won = games_won;
        this.games_played = games_played;
        this.total_margin = total_margin;
    }

    public int getTotal_margin() {
        return total_margin;
    }

    public int getGames_played() {
        return games_played;
    }

    public int getGames_won() {
        return games_won;
    }

    public Parameters getParameters() {
        return parameters;
    }
}
