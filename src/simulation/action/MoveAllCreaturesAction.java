package simulation.action;

import entity.*;
import worldmap.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class MoveAllCreaturesAction implements Action {

    @Override
    public void perform(WorldMap worldMap) {
        List<Entity> snapshot = new ArrayList<>(worldMap.getAllEntities());
        for (Entity entity : snapshot) {
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
