package simulation.action;

import entity.Creature;
import entity.Entity;
import map.Coordinates;
import map.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class ReproduceAction implements Action {

    Class<? extends Creature> creatureClass;

    public ReproduceAction(Class<? extends Creature> creatureClass) {
        this.creatureClass = creatureClass;
    }

    @Override
    public void perform(WorldMap worldMap) {
        List<Coordinates> birthPlaces = new ArrayList<>();
        List<Entity> newBabies = new ArrayList<>();

        for (Entity entity : worldMap.getAllEntities()) {
            if ((entity.getClass() == creatureClass)) {
                Creature parent1 = (Creature) entity;
                for (Entity otherEntity : worldMap.getAllEntities()) {
                    if ((entity != otherEntity) && (otherEntity.getClass() == creatureClass) && (isNeighborsCells(entity.getCoordinates(), otherEntity.getCoordinates()))) {
                        Creature parent2 = (Creature) otherEntity;
                        if ((parent1.getHealth() > parent1.getMaxHealth() / 2) && (parent2.getHealth() > parent2.getMaxHealth() / 2)) {
                            Coordinates babyPlace = findEmptyNeighborCell(parent1.getCoordinates(), worldMap);
                            if (babyPlace != null) {
                                try {
                                    Entity newBabyEntity = creatureClass.getDeclaredConstructor().newInstance();
                                    birthPlaces.add(babyPlace);
                                    newBabies.add(newBabyEntity);
                                    parent1.setHealth(parent1.getHealth() - parent1.getMaxHealth() / 2);
                                    parent2.setHealth(parent2.getHealth() - parent2.getMaxHealth() / 2);
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < newBabies.size(); i++) {
            worldMap.addEntity(birthPlaces.get(i), newBabies.get(i));
        }

    }

    private static boolean isNeighborsCells(Coordinates coordinatesEntity, Coordinates coordinatesOtherEntity) {
        int deltaX = Math.abs(coordinatesEntity.getX() - coordinatesOtherEntity.getX());
        int deltaY = Math.abs(coordinatesEntity.getY() - coordinatesOtherEntity.getY());
        return deltaX + deltaY == 1;
    }

    private static Coordinates findEmptyNeighborCell(Coordinates parent1, WorldMap worldMap) {
        List<Coordinates> neighbors = new ArrayList<>();
        neighbors.add(new Coordinates(parent1.getX() + 1, parent1.getY())); // Право
        neighbors.add(new Coordinates(parent1.getX() - 1, parent1.getY())); // Лево
        neighbors.add(new Coordinates(parent1.getX(), parent1.getY() + 1)); // Верх
        neighbors.add(new Coordinates(parent1.getX(), parent1.getY() - 1)); // Низ
        for (Coordinates neighbor : neighbors) {
            if ( (neighbor.getX() >= 0)
                    && (neighbor.getX() <= worldMap.getLength() - 1)
                    && ((neighbor.getY() >= 0) && (neighbor.getY() <= worldMap.getWidth() - 1)
                    && (worldMap.isCellEmpty(neighbor)))) {
                return neighbor;
            }
        }
        return null;
    }


}
