package entity;

import worldmap.Coordinates;
import worldmap.WorldMap;

import java.util.List;

public class Herbivore extends Creature {

    private static final int HP = 100;
    private static final int SPEED = 2;
    private static final Class<? extends Entity> FOOD = Grass.class;
    private static final int STARVATION_DAMAGE = 5;

    public Herbivore(int hp, int speed, Class<? extends Entity> food, int starvationDamage) {
        super(hp, speed, food, starvationDamage);
    }

    public Herbivore() {
        this(HP, SPEED, FOOD, STARVATION_DAMAGE);
    }

    @Override
    protected void interact(Coordinates targetEntity, WorldMap worldMap, List<Coordinates> path) {
        eat(targetEntity, worldMap);
    }
}
