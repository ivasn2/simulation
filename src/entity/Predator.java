
package entity;

import worldmap.Coordinates;
import worldmap.WorldMap;

import java.util.List;

public class Predator extends Creature {

    private final int attackPower;

    public Predator(int hp, int speed, int attackPower, int starvationDamage) {
        super(hp, speed, starvationDamage);
        this.attackPower = attackPower;
    }

    public Predator() {
        this(150, 2, 50, 10);
    }

    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public Class<? extends Entity> getTargetClass() {
        return Herbivore.class;
    }

    @Override
    public void interact(Coordinates targetCell, WorldMap worldMap, List<Coordinates> path) {
        Creature prey = (Creature) worldMap.getEntity(targetCell);
        prey.takeDamage(getAttackPower());

        if (prey.isDead()) {
            eat(targetCell, worldMap);

        } else if (path.size() > 1) {
            Coordinates cellBeforePrey = path.get(path.size() - 2);
            moveEntity(cellBeforePrey, worldMap);
        }
    }
}
