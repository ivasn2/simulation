package simulation.action;

import entity.Creature;
import entity.Entity;
import worldmap.Coordinates;
import worldmap.WorldMap;

import java.util.*;

public class ReproduceAction implements Action {

    private final Class<? extends Creature> creatureClass;

    public ReproduceAction(Class<? extends Creature> creatureClass) {
        this.creatureClass = creatureClass;
    }

    @Override
    public void perform(WorldMap worldMap) {
        Map<Coordinates, Creature> newBabiesMap = new HashMap<>();
        Set<Coordinates> reservedCells = new HashSet<>();

        for (Entity entity : worldMap.getAllEntities()) {

            if ((entity.getClass() == creatureClass)) {
                Creature parent1 = (Creature) entity;

                if (!parent1.canReproduce()) {
                    continue;
                }

                Creature parent2 = findNeighborPartner(parent1, worldMap);
                if ((parent2 != null) && (parent2.canReproduce())) {
                    Coordinates babyPlace = findEmptyNeighborCell(parent1.getCoordinates(), worldMap, reservedCells);

                    if (babyPlace != null) {
                        try {
                            Creature newBabyEntity = creatureClass.getDeclaredConstructor().newInstance();
                            newBabiesMap.put(babyPlace, newBabyEntity);
                            reservedCells.add(babyPlace);
                            parent1.setHealth(parent1.getHealth() - parent1.getMaxHealth() / 2);
                            parent2.setHealth(parent2.getHealth() - parent2.getMaxHealth() / 2);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
            }
        }
        for (Map.Entry<Coordinates, Creature> entry : newBabiesMap.entrySet()) {
            worldMap.addEntity(entry.getKey(), entry.getValue());
        }

    }

    private Creature findNeighborPartner(Creature parent, WorldMap worldMap) {

        Coordinates parentCoordinates = parent.getCoordinates();
        List<Coordinates> neighbors = parentCoordinates.neighborCells();

        for (Coordinates candidate : neighbors) {
            if (worldMap.isWithin(candidate)
                    && (!worldMap.isCellEmpty(candidate))
                    && (worldMap.getEntity(candidate).getClass() == creatureClass)) {
                return (Creature) worldMap.getEntity(candidate);
            }
        }
        return null;
    }

    private static Coordinates findEmptyNeighborCell(Coordinates centerCell, WorldMap worldMap, Set<Coordinates> reservedCells) {

        List<Coordinates> neighbors = centerCell.neighborCells();

        for (Coordinates neighbor : neighbors) {
            if (worldMap.isWithin(neighbor)
                    && worldMap.isCellEmpty(neighbor)
                    && (!reservedCells.contains(neighbor))) {
                return neighbor;
            }
        }
        return null;
    }
}

