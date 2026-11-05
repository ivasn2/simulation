
package entity;

import worldmap.Coordinates;
import worldmap.WorldMap;

import java.util.List;

public class Predator extends Creature {

    private final int attackPower;

    public Predator(String symbol, int hp, int speed, int attackPower, int starvationDamage) {
        super(symbol, hp, speed, starvationDamage);
        this.attackPower = attackPower;
    }

    public Predator() {
        this("\uD83D\uDC3A", 150, 2, 50, 10);
    }

    public int getAttackPower() {
        return attackPower;
    }

    @Override
    public Class<? extends Entity> getTargetClass() {
        return Herbivore.class;
    }

    @Override
    public void interact(Entity targetEntity, WorldMap worldMap, List<Coordinates> path) {
        Creature prey = (Creature) worldMap.getEntity(targetEntity.getCoordinates());
        prey.takeDamage(getAttackPower());

        if (prey.isDead()) {
            eat(targetEntity, worldMap);

        } else if (path.size() > 1) {
            Coordinates cellBeforePrey = path.get(path.size() - 2);
            worldMap.moveEntity(cellBeforePrey, this);
        }
    }
}
