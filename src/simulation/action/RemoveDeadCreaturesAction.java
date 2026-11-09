package simulation.action;

import entity.Creature;
import entity.Entity;
import worldmap.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class RemoveDeadCreaturesAction implements  Action {

    @Override
    public void perform(WorldMap worldMap) {
        List<Entity> snapshot = new ArrayList<>(worldMap.getAllEntities());
        for (Entity entity : snapshot) {
            if (entity instanceof Creature) {
                Creature creature = (Creature) entity;
                if (creature.isDead()) {
                    worldMap.removeEntity(creature.getCoordinates());
                }
            }
        }
    }
}
