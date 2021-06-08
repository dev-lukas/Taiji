import java.util.Objects;

public class BoardHash {
    private final LongLong x;
    private final LongLong y;

    public BoardHash(LongLong whites, LongLong blacks) {
        this.x = whites;
        this.y = blacks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoardHash b)) return false;
        return x == b.x && y == b.y;
    }

    @Override
    public int hashCode() {
        int result = 256;
        int c1 = (int)(x.HI ^ (x.HI >>> 32));
        int c2 = (int)(x.LO ^ (x.LO >>> 32));
        int c3 = (int)(y.HI ^ (y.HI >>> 32));
        int c4 = (int)(y.LO ^ (y.LO >>> 32));
        return 37 * result + c1 + c2 + c3 + c4;
    }
}
