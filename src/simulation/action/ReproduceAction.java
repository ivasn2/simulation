package simulation.action;

import entity.Creature;
import entity.Entity;
import map.Coordinates;
import map.WorldMap;

public class ReproduceAction implements Action {

    Class<? extends Creature> creatureClass;

    public ReproduceAction(Class<? extends Creature> creatureClass) {
        this.creatureClass = creatureClass;
    }

    @Override
    public void perform(WorldMap worldMap) {
        for (Entity entity : worldMap.getAllEntities()) {
            if ((entity.getClass() == creatureClass)) {
                Creature parent1 = (Creature) entity;
                for (Entity otherEntity : worldMap.getAllEntities()) {
                    if ((entity != otherEntity) && (entity.getClass() == creatureClass) && (isNeighborsCells(entity.getCoordinates(), otherEntity.getCoordinates()))) {
                        Creature parent2 = (Creature) otherEntity;
                        if ((parent1.getHealth() > parent1.getMaxHealth() / 2) && (parent2.getHealth() > parent2.getMaxHealth() / 2)) {

                        }
                    }
                }
            }
        }
    }

    private static boolean isNeighborsCells(Coordinates coordinatesEntity, Coordinates coordinatesOtherEntity) {
        int deltaX = Math.abs(coordinatesEntity.getX() - coordinatesOtherEntity.getX());
        int deltaY = Math.abs(coordinatesEntity.getY() - coordinatesOtherEntity.getY());
        return deltaX + deltaY == 1;
    }

    private static Coordinates findEmptyNeighborCell(Coordinates center, WorldMap worldMap) {

    }


}
