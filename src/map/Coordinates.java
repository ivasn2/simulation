package map;

import java.util.Objects;

public class Coordinates {

    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinates) {
            return ((((Coordinates) obj).getX() == this.x) && ((((Coordinates) obj).getY() == this.y)));
        }
        return false;
    }


}
