
package entity;

import map.Coordinates;
import map.WorldMap;
import simulation.BreadthFirstSearch;

public class Predator extends Creature {

    private int attackPower;

    public Predator(String symbol, int hp, int speed, int attackPower, int starvationDamage) {
        super(symbol, hp, speed, starvationDamage);
        this.attackPower = attackPower;
    }

    public Predator() {
        this("🐻", 150, 1, 50, 20);
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    @Override
    public void makeMove(WorldMap worldMap) {

        takeDamage(getStarvationDamage());
        if (this.isDead()) {
            worldMap.removeEntity(this.getCoordinates());
            return;
        }

        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();

        Coordinates oldCoordinates = getCoordinates();
        Coordinates newCoordinates = breadthFirstSearch.breadthFirstSearch(getCoordinates(), worldMap, Herbivore.class);
        Creature target;

        if (newCoordinates != null) {

            if (worldMap.getEntity(newCoordinates) instanceof Herbivore) {
                target = (Creature) worldMap.getEntity(newCoordinates);
                target.takeDamage(getAttackPower());
                if (target.isDead()) {
                    worldMap.removeEntity(target.getCoordinates());
                    worldMap.removeEntity(oldCoordinates);
                    worldMap.addEntity(newCoordinates, this);
                    setHealth(getMaxHealth());
                }
            } else if (worldMap.isCellEmpty(newCoordinates)) {
                worldMap.removeEntity(oldCoordinates);
                worldMap.addEntity(newCoordinates, this);
            }
        }
    }
}
