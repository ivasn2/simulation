package simulation.action;

import entity.*;
import map.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class MoveAllCreaturesAction implements Action {


    @Override
    public void perform(WorldMap worldMap) {

        List<Entity> copyGetAllEntities = new ArrayList<>(worldMap.getAllEntities());
        for (Entity entity : copyGetAllEntities) {
            if (entity instanceof Creature) {
                ((Creature) entity).makeMove(worldMap);
            }
        }
    }


}
