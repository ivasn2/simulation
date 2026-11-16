
package entity;

import worldmap.Coordinates;
import worldmap.WorldMap;

import java.util.List;

public class Predator extends Creature {

    private final int attackPower;
    private static final int ATTACK_POWER = 50;
    private static final int HP = 150;
    private static final int SPEED = 2;
    private static final Class<? extends Entity> FOOD = Herbivore.class;
    private static final int STARVATION_DAMAGE = 10;

    public Predator(int hp, int speed, Class<? extends Entity> food, int attackPower, int starvationDamage) {
        super(hp, speed, food, starvationDamage);
        this.attackPower = attackPower;
    }

    public Predator() {
        this(HP, SPEED, FOOD, ATTACK_POWER, STARVATION_DAMAGE);
    }

    private int getAttackPower() {
        return attackPower;
    }

    @Override
    protected void interact(Coordinates targetCoordinates, WorldMap worldMap, List<Coordinates> path) {
        Creature prey = (Creature) worldMap.getEntity(targetCoordinates);
        prey.takeDamage(getAttackPower());

        if (prey.isDead()) {
            eat(targetCoordinates, worldMap);

        } else if (path.size() > 1) {
            Coordinates cellBeforePrey = path.get(path.size() - 2);
            moveEntity(cellBeforePrey, worldMap);
        }
    }
}
