package entity;

public abstract class Creature extends Entity {

    private int health;
    private int speed;

    abstract void makeMove();

    public Creature(String symbol, int hp, int speed) {
        super(symbol);
        this.health = hp;
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
