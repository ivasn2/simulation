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
    public void makeMove(WorldMap worldMap) {
        takeDamage(getStarvationDamage());
        if (this.isDead()) {
            worldMap.removeEntity(this.getCoordinates());
            return;
        }

        Coordinates oldCoordinates = getCoordinates();
        List<Coordinates> path = BreadthFirstSearch.findPath(getCoordinates(), worldMap, Grass.class);
        if (!path.isEmpty()) {
            int stepsToTake = Math.min(path.size(), getSpeed());
            Coordinates targetCell = path.get(stepsToTake - 1);

            if (targetCell.equals(path.getLast())) {
                worldMap.removeEntity(targetCell);
                worldMap.removeEntity(oldCoordinates);
                worldMap.addEntity(targetCell, this);
                setHealth(getMaxHealth());
            } else {
                worldMap.removeEntity(oldCoordinates);
                worldMap.addEntity(targetCell, this);
            }
        }
    }
}
