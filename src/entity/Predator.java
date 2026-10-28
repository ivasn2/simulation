
package entity;

import map.Coordinates;
import map.WorldMap;
import simulation.BreadthFirstSearch;

import java.util.List;

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

        Coordinates oldCoordinates = getCoordinates();
        List<Coordinates> path = BreadthFirstSearch.findPath(getCoordinates(), worldMap, Herbivore.class);
        if (!path.isEmpty()) {
            int stepsToTake = Math.min(path.size(), getSpeed());
            Coordinates targetCell = path.get(stepsToTake - 1);

            if (targetCell.equals(path.getLast())) {
                Creature target = (Creature) worldMap.getEntity(targetCell);
                target.takeDamage(getAttackPower());

                if (target.isDead()) {
                    worldMap.removeEntity(targetCell);
                    worldMap.removeEntity(oldCoordinates);
                    worldMap.addEntity(targetCell, this);
                    setHealth(getMaxHealth());
                } else if (path.size() > 1) {
                    Coordinates penultimateCell = path.get(path.size() - 2);
                    worldMap.removeEntity(oldCoordinates);
                    worldMap.addEntity(penultimateCell, this);
                }

            } else {
                worldMap.removeEntity(oldCoordinates);
                worldMap.addEntity(targetCell, this);
            }
        }
    }
}
