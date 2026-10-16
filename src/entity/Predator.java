package entity;

import map.Coordinates;
import map.WorldMap;
import simulation.BreadthFirstSearch;

public class Predator extends Creature {

    private int attackPower;

    public Predator(String symbol, int hp, int speed, int attackPower) {
        super(symbol, hp, speed);
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    @Override
    public void makeMove(WorldMap worldMap) {

        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();

        Coordinates oldCoordinates = getCoordinates();
        Coordinates newCoordinates = breadthFirstSearch.breadthFirstSearch(getCoordinates(), worldMap, Herbivore.class);

        if (newCoordinates != null) {
            if (worldMap.getEntity(newCoordinates) instanceof Herbivore) {
                worldMap.removeEntity(newCoordinates);
            }

            worldMap.removeEntity(oldCoordinates);
            worldMap.addEntity(newCoordinates, this);
        }

    }
}
