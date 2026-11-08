package entity;

import worldmap.Coordinates;
import worldmap.WorldMap;

import java.util.List;

public class Herbivore extends Creature {

    public Herbivore(int hp, int speed, int starvationDamage) {
        super(hp, speed, starvationDamage);
    }

    public Herbivore() {
        this(100, 2, 5);
    }


    @Override
    public Class<? extends Entity> getTargetClass() {
        return Grass.class;
    }

    @Override
    public void interact(Coordinates targetEntity, WorldMap worldMap, List<Coordinates> path) {
        eat(targetEntity, worldMap);
    }
}
