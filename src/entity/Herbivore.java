package entity;

import worldmap.Coordinates;
import worldmap.WorldMap;

import java.util.List;

public class Herbivore extends Creature {

    public Herbivore(int hp, int speed, Class<? extends Entity> food, int starvationDamage) {
        super(hp, speed, food, starvationDamage);
    }

    public Herbivore() {
        this(100, 2, Grass.class, 5);
    }

    @Override
    public void interact(Coordinates targetEntity, WorldMap worldMap, List<Coordinates> path) {
        eat(targetEntity, worldMap);
    }
}
