package entity;

import map.Coordinates;
import map.WorldMap;
import simulation.BreadthFirstSearch;

import java.util.List;

public class Herbivore extends Creature {

    public Herbivore(String symbol, int hp, int speed, int starvationDamage) {
        super(symbol, hp, speed, starvationDamage);
    }

    public Herbivore() {
        this("🫎", 100, 1, 10);
    }


    @Override
    public Class<? extends Entity> getTargetClass() {
        return Grass.class;
    }

    @Override
    public void interact(Entity target, WorldMap worldMap, List<Coordinates> path) {
        worldMap.removeEntity(target.getCoordinates());
        worldMap.removeEntity(getCoordinates());
        worldMap.addEntity(target.getCoordinates(), this);
        setHealth(getMaxHealth());
    }
}
