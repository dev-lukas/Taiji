import java.util.*;

public class Board {
    /* for a board
    "---wbbw/-----b-/-----w-/b------/wb-----/ww-----/bwb----"       ->      - - - w b b w
                                                                            - - - - - b -
                                                                            - - - - - w -
                                                                            b - - - - - -
                                                                            w b - - - - -
                                                                            w w - - - - -
                                                                            b w b - - - -
    we get have the representation :
    whites : 0001001000000000000100000000100000011000000100000      ->      0 0 0 1 0 0 1
                                                                            0 0 0 0 0 0 0
                                                                            0 0 0 0 0 1 0
                                                                            0 0 0 0 0 0 0
                                                                            1 0 0 0 0 0 0
                                                                            1 1 0 0 0 0 0
                                                                            0 1 0 0 0 0 0

    blacks : 0000110000001000000001000000010000000000001010000      ->      0 0 0 0 1 1 0
                                                                            0 0 0 0 0 1 0
                                                                            0 0 0 0 0 0 0
                                                                            1 0 0 0 0 0 0
                                                                            0 1 0 0 0 0 0
                                                                            0 0 0 0 0 0 0
                                                                            1 0 1 0 0 0 0
    We don't use the rest of the 128 bits

    */
    public LongLong whites;
    public LongLong blacks;
    private List<Zug> moves;
    final int n;

    public Board(String strBoard) {
        strBoard = strBoard.replace("/","");
        moves = new ArrayList<>();
        char[] charsBoard = strBoard.toCharArray();


        this.n = (int)Math.sqrt(charsBoard.length);

        whites = new LongLong(0,0);
        blacks = new LongLong(0,0);

        // mask for adding tiles
        // in the end whites and blacks are the same as the input board string but in bits and showing the position of white and black tiles separately
        LongLong a;
        if (n == 11) a = new LongLong(0,0b0000000000000000000000000000000000000000000000000000000010000000L);
        else if (n == 9) a = new LongLong(0,0b0000000000000000100000000000000000000000000000000000000000000000L);
        else a = new LongLong(0b0000000000000000000000000000000000000000000000001000000000000000L,0);



        for(int i=0; i < n*n; i++){
            if (charsBoard[i] == 'w') {
                whites = whites.OR(a);
            }
            else if (charsBoard[i] == 'b'){
                blacks = blacks.OR(a);
            }

            if ( i < n*n - 1) {
                whites = whites.LSHIFT(1);
                blacks = blacks.LSHIFT(1);
            }
        }
    }

    public List<Zug> moveGenerator(){
        // occupied
        LongLong o = whites.OR(blacks);

        // mask for checking horizontal
        LongLong ch = new LongLong(0b1100000000000000000000000000000000000000000000000000000000000000L,0);
        for(int i=0; i < n; i++){
            for(int k=0; k < n-1 ;k++) {
                if (o.AND(ch).isZero()) {
                    moves.add(new Zug(i,k,i,k+1));
                    moves.add(new Zug(i,k+1,i,k));
                }
                if (k < n - 2 ) ch = ch.RSHIFT(1);
            }
            ch = ch.RSHIFT(2);
        }

        // mask for checking vertical
        LongLong cv;
        if (n == 11) cv = new LongLong(0b1000000000010000000000000000000000000000000000000000000000000000L,0);
        else if (n == 9) cv = new LongLong(0b1000000001000000000000000000000000000000000000000000000000000000L,0);
        else cv = new LongLong(0b1000000100000000000000000000000000000000000000000000000000000000L,0);

        for(int i=0; i < n-1; i++){
            for(int k=0; k < n ;k++) {
                if (o.AND(cv).isZero()) {
                    moves.add(new Zug(i,k,i+1,k));
                    moves.add(new Zug(i+1,k,i,k));
                }
                cv = cv.RSHIFT(1);
            }
        }
        return moves;
    }

    public List<Zug> getMoves() {
        return moves;
    }
    public void setMOves(List<Zug> moves){
        this.moves = moves;
    }

    public void doMove(Zug zug){
        LongLong w = new LongLong(0b1000000000000000000000000000000000000000000000000000000000000000L,0);
        LongLong b = new LongLong(0b1000000000000000000000000000000000000000000000000000000000000000L,0);

        w = w.RSHIFT(zug.whiteX * n + zug.whiteY );
        this.whites = this.whites.OR(w);
        b = b.RSHIFT(zug.blackX * n + zug.blackY);
        this.blacks = this.blacks.OR(b);
    }

    public String getRandomMove(){
        Random rand = new Random();
        if (moves.size() != 0) {
            Zug m = moves.get(rand.nextInt(moves.size()));
            return  (m.whiteX * n + m.whiteY) + "," + (m.blackX * n + m.blackY);
        } else return null;
    }

    // Bewertungsfunktion
    public int h(){
        return 0;
    };

    public String toString(){
        LongLong m = new LongLong(0b1000000000000000000000000000000000000000000000000000000000000000L,0);
        String board = "";
        for(int i=0; i < n; i++){
            for(int k=0; k < n; k++){
                if (!whites.AND(m).isZero() ) board += "w ";
                else if (!blacks.AND(m).isZero()) board += "b ";
                else board += "- ";
                m = m.RSHIFT(1);
            }
            board += "\n";
        }
        return board;
    }
}


