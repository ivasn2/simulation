
package entity;

import worldmap.Coordinates;
import worldmap.WorldMap;

import java.util.List;

public class Predator extends Creature {

    private final int attackPower;

    public Predator(int hp, int speed, Class<? extends Entity> food, int attackPower, int starvationDamage) {
        super(hp, speed, food, starvationDamage);
        this.attackPower = attackPower;
    }

    public Predator() {
        this(150, 2, Herbivore.class, 50, 10);
    }

    public int getAttackPower() {
        return attackPower;
    }

    @Override
    protected void interact(Coordinates targetCell, WorldMap worldMap, List<Coordinates> path) {
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
