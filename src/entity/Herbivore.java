package entity;

import map.Coordinates;
import map.WorldMap;
import simulation.BreadthFirstSearch;

public class Herbivore extends Creature {

    public Herbivore(String symbol, int hp, int speed) {
        super(symbol, hp, speed);
    }

    public Herbivore() {
        this("🫎", 100, 2); // Передаем дефолтные настройки обычного оленя
    }

    @Override
    public void makeMove(WorldMap worldMap) {


        if (!starve(worldMap)) {
            return;
        }

        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();

        Coordinates oldCoordinates = getCoordinates();
        Coordinates newCoordinates = breadthFirstSearch.breadthFirstSearch(getCoordinates(), worldMap, Grass.class);

        if (newCoordinates != null) {
            if (worldMap.getEntity(newCoordinates) instanceof Grass) {
                worldMap.removeEntity(newCoordinates);
                setHealth(getMaxHealth());
            }

            worldMap.removeEntity(oldCoordinates);
            worldMap.addEntity(newCoordinates, this);
        }
    }
}
