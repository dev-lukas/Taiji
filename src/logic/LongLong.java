package logic;

public class LongLong {
    public long HI;
    public long LO;
    private int n;
    // 128 bits

    /*
        first 49, 81 or 121 used, the other should be 0
        from left to right is HI than LO

     */
    public LongLong(long a, long b){
        HI=a;
        LO=b;
    }
    // |
    public LongLong OR(LongLong a){
        LongLong result = new LongLong(0,0);
        result.HI = a.HI | this.HI;
        result.LO = a.LO | this.LO;
        return result;
    }
    // &
    public LongLong AND(LongLong a){
        LongLong result = new LongLong(0,0);
        result.HI = a.HI & this.HI;
        result.LO = a.LO & this.LO;
        return result;
    }
    // ~
    public LongLong NEG(LongLong a){
        LongLong result = new LongLong(0,0);
        result.HI = ~a.HI;
        result.LO = ~a.LO;
        return result;
    }
    // >> for 11x11
    public LongLong RSHIFT(int l){
        LongLong result = new LongLong(0,0);
        long temp;
        temp = this.HI;
        temp = temp << (64 - l);
        // mask to turn the last sever bits to 0
        long m = 0b1111111111111111111111111111111111111111111111111111111110000000L;
        result.LO = ( ( this.LO >>> l) | temp) & m;
        //result.LO = ( this.LO >>> l) | temp;
        result.HI = this.HI >>> l;

        return result;
    }
    // << for 11X11
    public LongLong LSHIFT(int l){
        LongLong result = new LongLong(0,0);
        long temp;
        temp = this.LO;
        temp = temp >>> (64 - l);
        // mask to turn the last sever bits to 0
        long m = 0b1111111111111111111111111111111111111111111111111111111110000000L;
        result.HI =  this.HI << l | temp;
        result.LO = this.LO << l & m;
        //result.LO = this.LO << l;

        return result;
    }

    public boolean isZero(){
        if (this.HI == 0 && this.LO == 0) return true;
        return false;
    }



    public String toString(){
        String hi = "";
        String lo = "";
        for (int i = 0; i < 64 - Long.toBinaryString(this.HI).length();i++){
            hi += "0";
        }
        for (int i = 0; i < 64 - Long.toBinaryString(this.LO).length();i++){
            lo += "0";
        }
        return hi + Long.toBinaryString(this.HI) + lo + Long.toBinaryString(this.LO);
    }

}
