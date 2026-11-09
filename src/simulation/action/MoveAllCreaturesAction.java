package simulation.action;

import entity.*;
import worldmap.WorldMap;

public class MoveAllCreaturesAction implements Action {

    @Override
    public void perform(WorldMap worldMap) {
        for (Entity entity : worldMap.getAllEntities()) {
            if (entity instanceof Creature) {
                Creature creature = (Creature) entity;
                if (!worldMap.isCellEmpty(creature.getCoordinates())) {
                    if (worldMap.getEntity(creature.getCoordinates()) == entity) {
                        creature.makeMove(worldMap);
                    }
                }
            }
        }
    }
}
