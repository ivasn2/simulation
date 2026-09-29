package simulation.action;

import entity.*;
import map.WorldMap;

public class MoveAllCreaturesAction implements Action {

    @Override
    public void perform(WorldMap worldMap) {
        for (Entity entity : worldMap.getAllEntities()) {
            if (entity instanceof Creature) {
                ((Creature) entity).makeMove();
            }
        }
    }


}
