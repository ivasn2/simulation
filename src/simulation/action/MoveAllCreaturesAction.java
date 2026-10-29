package simulation.action;

import entity.*;
import map.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class MoveAllCreaturesAction implements Action {

    @Override
    public void perform(WorldMap worldMap) {
        List<Entity> snapshot = new ArrayList<>(worldMap.getAllEntities());
        for (Entity entity : snapshot) {
            if ((entity instanceof Creature) && (worldMap.getEntity(entity.getCoordinates()) == entity)){
                ((Creature) entity).makeMove(worldMap);
            }
        }
    }


}
