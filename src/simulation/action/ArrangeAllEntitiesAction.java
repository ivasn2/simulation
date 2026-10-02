package simulation.action;

import entity.Coordinates;
import entity.Entity;
import entity.Grass;
import map.WorldMap;

public class ArrangeAllEntitiesAction implements Action {

    @Override
    public void perform(WorldMap worldMap) {
        Coordinates coordinates = new Coordinates(0, 0);
        Entity entity = new Grass();
        worldMap.addEntity(coordinates, entity);
    }
}
