public class Zug {
    public int whiteX;
    public int whiteY;
    public int blackX;
    public int blackY;

    public Zug(int wx, int wy, int bx, int by){
        this.whiteX = wx;
        this.whiteY = wy;
        this.blackX = bx;
        this.blackY = by;
    }

    public String toString(){
        return "w(" + whiteX + "," + whiteY + ")-b(" + + blackX + "," + blackY + ")";
    }

}
