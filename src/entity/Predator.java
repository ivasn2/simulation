
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
    public Class<? extends Entity> getTargetClass() {
        return Herbivore.class;
    }

    @Override
    public void interact(Entity target, WorldMap worldMap, List<Coordinates> path) {
        Creature target1 = (Creature) worldMap.getEntity(target.getCoordinates());
        target1.takeDamage(getAttackPower());

        if (target1.isDead()) {
            worldMap.removeEntity(target.getCoordinates());
            worldMap.removeEntity(getCoordinates());
            worldMap.addEntity(target.getCoordinates(), this);
            setHealth(getMaxHealth());

        } else if (path.size() > 1) {
            Coordinates penultimateCell = path.get(path.size() - 2);
            worldMap.removeEntity(getCoordinates());
            worldMap.addEntity(penultimateCell, this);
        }
    }
}
