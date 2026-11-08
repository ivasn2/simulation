package entity;

public abstract class Entity {

    private final String symbol;

    public String getSymbol() {
        return symbol;
    }

    public Entity(String symbol) {
        this.symbol = symbol;
    }
}
