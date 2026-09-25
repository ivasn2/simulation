package entity;

public abstract class Entity {

    private Coordinates coordinates;
    private final String symbol;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Entity(String symbol) {
        this.symbol = symbol;
    }
}
