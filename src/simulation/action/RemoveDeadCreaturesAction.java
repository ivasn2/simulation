package simulation.action;

import entity.Creature;
import entity.Entity;
import worldmap.WorldMap;

public class RemoveDeadCreaturesAction implements  Action {

    @Override
    public void perform(WorldMap worldMap) {
        for (Entity entity : worldMap.getAllEntities()) {
            if (entity instanceof Creature) {
                Creature creature = (Creature) entity;
                if (creature.isDead()) {
                    worldMap.removeEntity(creature.getCoordinates());
                }
            }
        }
    }
}
