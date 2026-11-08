package entity;

import worldmap.Coordinates;
import worldmap.WorldMap;
import simulation.BreadthFirstSearch;

import java.util.List;

public abstract class Creature extends Entity {

    private Coordinates coordinates;
    private int health;
    private final int maxHealth;
    private final int speed;
    private final Class<? extends Entity> food;
    private final int starvationDamage;

    public Creature(int hp, int speed, Class<? extends Entity> food, int starvationDamage) {
        this.health = hp;
        this.food = food;
        this.maxHealth = health;
        this.speed = speed;
        this.starvationDamage = starvationDamage;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getSpeed() {
        return speed;
    }

    public int getStarvationDamage() {
        return starvationDamage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean canReproduce() {
        return this.health > this.maxHealth / 2;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
    }

    public boolean isDead() {
        return this.health <= 0;
    }

    public Class<? extends Entity> getFood() {
        return food;
    }

    public abstract void interact(Coordinates targetCell, WorldMap worldMap, List<Coordinates> path);

    public void makeMove(WorldMap worldMap) {

        takeDamage(getStarvationDamage());

        if (isDead()) {
            worldMap.removeEntity(getCoordinates());
            return;
        }

        List<Coordinates> path = BreadthFirstSearch.findPath(getCoordinates(), worldMap, getFood());

        if (!path.isEmpty()) {
            int stepCount = Math.min(path.size(), getSpeed());
            Coordinates targetCell = path.get(stepCount - 1);
            if (targetCell.equals(path.getLast())) {
                interact(targetCell, worldMap, path);
            } else {
                moveEntity(targetCell, worldMap);
            }
        }
    }

    protected void eat(Coordinates targetCell, WorldMap worldMap) {
        worldMap.removeEntity(targetCell);
        moveEntity(targetCell, worldMap);
        setHealth(getMaxHealth());
    }

    protected void moveEntity(Coordinates destination,  WorldMap worldMap) {
        worldMap.removeEntity(getCoordinates());
        worldMap.addEntity(destination, this);
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }
}
