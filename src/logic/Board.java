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


    final List<Zug> moves;
    final int n;
    // color of maximizing player
    final String c;


    // this constructor will be called only once per turn, it calls the moveGenerator
    public Board(String strBoard,String color) {
        strBoard = strBoard.replace("/","");
        moves = new ArrayList<>();
        char[] charsBoard = strBoard.toCharArray();


        this.n = (int)Math.sqrt(charsBoard.length);

        this.c = color;

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

        moveGenerator();

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



    // this constructor will be called for all iterations of alphabeta with the respective move
    public Board(Board board,Zug z){
        this.moves = new ArrayList<>();
        this.whites = board.whites;
        this.blacks = board.blacks;
        this.n = board.n;
        this.c = board.c;
        this.doMove(z);
        moveGenerator();
    }

    public List<Zug> getMoves() {
        return moves;
    }

    public void removeMove(Zug z){
        this.moves.remove(new Zug(z));
    }

    public void doMove(Zug zug){
        LongLong w = new LongLong(0b1000000000000000000000000000000000000000000000000000000000000000L,0);
        LongLong b = new LongLong(0b1000000000000000000000000000000000000000000000000000000000000000L,0);

        w = w.RSHIFT(zug.whiteX * n + zug.whiteY );
        this.whites = this.whites.OR(w);
        b = b.RSHIFT(zug.blackX * n + zug.blackY);
        this.blacks = this.blacks.OR(b);
    }

    // for the first milestone
    public String getRandomMove(){
        Random rand = new Random();
        if (moves.size() != 0) {
            Zug m = moves.get(rand.nextInt(moves.size()));
            return  (m.whiteX * n + m.whiteY) + "," + (m.blackX * n + m.blackY);
        } else return null;
    }

    int count;
    // Bewertungsfunktion
    void recursiveRating(int i,boolean[] visited, LongLong a) {
        visited[i] = true;
        int[] values = {1,n};
        int[] values2 = {i-1,i-n};
        int[] values3 = {i+1,i+n};
        for(int j = 0; j < 2; j++) {
            if(a.RSHIFT(values[j]).toString().charAt(i) == 49 && !visited[values2[j]] && ((i)%n != 0 || j== 1 )) {
                count++;
                recursiveRating(values2[j], visited, a);
            }
            if(i<=n*n &&a.LSHIFT(values[j]).toString().charAt(i) == 49 && !visited[values3[j]] && ((i+1)%n != 0 || j== 1 )) {
                count++;
                recursiveRating(values3[j], visited, a);
            }
        }
    }

    public int evaluation(LongLong a){
        boolean[] visited = new boolean[n*n];
        List<Integer> values = new ArrayList<>();
        values.add(0);
        values.add(0);
        values.add(0);
        for(int i = 0; i < n*n; i++) {
            if(a.toString().charAt(i) == 49 && !visited[i]) {
                count = 1;
                recursiveRating(i, visited, a);
                values.add(count);
            }
        }
        values.sort(Collections.reverseOrder());
        if(n==7) return values.get(0);
        if(n==9) return values.get(0) + values.get(1);
        return values.get(0) + values.get(1) + values.get(2);
    }

    public int h(){
       if (c.equals("w")) {
           return evaluation(whites) - evaluation(blacks);
       } else {
           return evaluation(blacks) - evaluation(whites);
       }
    }

    public int ratingfunction(Parameters p, Zug z){
        int erg;
        if (c.equals("w")){
            erg = evaluation(whites) * 100;
            for(int i = 0; i< evaluation(whites); i++){
                erg += i * p.getIncrementPoints();
            }
        }else{
            erg = evaluation(blacks) * 100;
            for(int i = 0; i< evaluation(blacks); i++){
                erg += i * p.getIncrementPoints();
            }
        }
        if(z != null) {
            if (z.blackY == 0 || z.whiteY == 0 || z.blackY == n-1 || z.whiteY == n-1){
                erg += p.getEdge();
            }else if (z.blackX == 0 || z.whiteX == 0 || z.blackX == n-1 || z.whiteX == n-1){
                erg += p.getEdge();
            }
            if (z.blackX > 2 && z.blackX < 6 && z.blackY > 2 && z.blackY < 6 || z.whiteX > 2 && z.whiteX < 6 && z.whiteY > 2 && z.whiteY < 6){
                erg += p.getMiddle();
            }

            LongLong all = whites.OR(blacks);
            int white = z.whiteX * 7 + z.whiteY;
            int black = z.blackX * 7 +z.blackY;

            if (white > 6 && all.toString().charAt(white - 7) == 49 ||  black > 6 && all.toString().charAt(black - 7) == 49){
                erg += p.getOnAnother();
            }
            else if (white < 43 && all.toString().charAt(white + 7) == 49 || black < 43 && all.toString().charAt(black + 7) == 49 ){
                erg += p.getOnAnother();
            }
            else if (white >= 1 && (white+1) % 7 != 0 && all.toString().charAt(white - 1) == 49 || black >= 1 && (black +1) % 7!= 0  && all.toString().charAt(black - 1) == 49){
                erg += p.getOnAnother();
            }
            else if (white % 7 != 0  && all.toString().charAt(white + 1) == 49 || black % 7 != 0 && all.toString().charAt(black + 1) == 49 ){
                erg += p.getOnAnother();
            }
        }

        return erg;
    }

    // from Zug to String "x,y"
    public String parseMove(Zug m){
        return  (m.whiteX * n + m.whiteY) + "," + (m.blackX * n + m.blackY);
    }

    public String toBoardString() {
        LongLong m = new LongLong(0b1000000000000000000000000000000000000000000000000000000000000000L,0);
        String board = "";
        for(int i=0; i < n; i++){
            for(int k=0; k < n; k++){
                if (!whites.AND(m).isZero() ) board += "w";
                else if (!blacks.AND(m).isZero()) board += "b";
                else board += "-";
                m = m.RSHIFT(1);
            }
            if(i != n-1) {
                board += "/";
            }
        }
        return board;
    }

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


