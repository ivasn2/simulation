package entity;

import map.Coordinates;
import map.WorldMap;
import simulation.BreadthFirstSearch;

public class Herbivore extends Creature {

    public Herbivore(String symbol, int hp, int speed) {
        super(symbol, hp, speed);
    }

    @Override
    public void makeMove(WorldMap worldMap) {
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        Grass grass = new Grass();
        Coordinates oldCoordinates = getCoordinates();
        Coordinates newCoordinates = breadthFirstSearch.breadthFirstSearch(getCoordinates(), worldMap, grass);
        if (newCoordinates != null) {
            if (worldMap.getEntity(newCoordinates) instanceof Grass) {
                worldMap.removeEntity(newCoordinates);
            }

            worldMap.removeEntity(oldCoordinates);
            worldMap.addEntity(newCoordinates, this);
        }
    }
}
