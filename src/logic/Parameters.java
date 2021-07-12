public class Parameters {

    public int edge; //Set Stone on an edge
    public int middle; //Set Stone middle
    public int incrementPoints; //Point increment per extra connected field
    public int onAnother; //Put Stone onto another one, not necessarily exactly there

    public Parameters(int edge, int middle, int incrementPoints, int onAnother) {
        this.edge = edge;
        this.middle = middle;
        this.incrementPoints = incrementPoints;
        this.onAnother = onAnother;
    }

    public int getEdge() {
        return edge;
    }

    public int getIncrementPoints() {
        return incrementPoints;
    }

    public int getOnAnother() {
        return onAnother;
    }

    public int getMiddle() {
        return middle;
    }
}

