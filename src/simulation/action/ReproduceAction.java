package simulation.action;

import entity.Creature;
import entity.Entity;
import simulation.NeighborsFinder;
import worldmap.Coordinates;
import worldmap.WorldMap;
import java.util.*;
import java.util.function.Supplier;

public class ReproduceAction implements Action {

    private final Supplier<Creature> creatureCreator;

    public ReproduceAction(Supplier<Creature> creatureCreator) {
        this.creatureCreator = creatureCreator;
    }

    @Override
    public void perform(WorldMap worldMap) {
        Map<Coordinates, Creature> newBabiesMap = new HashMap<>();
        Set<Coordinates> reservedCells = new HashSet<>();

        for (Entity entity : worldMap.getAllEntities()) {

            if (!(entity instanceof Creature)) {
                continue;
            }

            Creature parent1 = (Creature) entity;
            if (!parent1.canReproduce()) {
                continue;
            }

            Optional<Creature> parent2 = findNeighborPartner(parent1, worldMap);

            if (parent2.isEmpty()) {
                continue;
            }
            Creature partner = parent2.get();
            Optional<Coordinates> babyPlace = findEmptyNeighborCell(parent1.getCoordinates(), worldMap, reservedCells);

            if (babyPlace.isEmpty()) {
                continue;
            }
            Coordinates babyCoordinates = babyPlace.get();

            Creature newBabyEntity = creatureCreator.get();
            newBabiesMap.put(babyCoordinates, newBabyEntity);
            reservedCells.add(babyCoordinates);
            parent1.setHealth(parent1.getHealth() - parent1.getMaxHealth() / 2);
            partner.setHealth(partner.getHealth() - partner.getMaxHealth() / 2);

        }

        for (Map.Entry<Coordinates, Creature> entry : newBabiesMap.entrySet()) {
            worldMap.addEntity(entry.getKey(), entry.getValue());
        }
    }

    private Optional<Creature> findNeighborPartner(Creature parent, WorldMap worldMap) {

        Coordinates parentCoordinates = parent.getCoordinates();
        List<Coordinates> neighbors = NeighborsFinder.neighborCells(parentCoordinates);

        for (Coordinates candidate : neighbors) {
            if (worldMap.isWithin(candidate)
                    && !worldMap.isCellEmpty(candidate)
                    && worldMap.getEntity(candidate) instanceof Creature) {
                Creature partner = (Creature) worldMap.getEntity(candidate);
                if ((partner.getClass() == parent.getClass()) && (partner.canReproduce())) {
                    return Optional.of(partner);
                }
            }
        }
        return Optional.empty();
    }

    private Optional<Coordinates> findEmptyNeighborCell(Coordinates centerCell, WorldMap worldMap, Set<Coordinates> reservedCells) {

        List<Coordinates> neighbors = NeighborsFinder.neighborCells(centerCell);

        for (Coordinates neighbor : neighbors) {
            if (worldMap.isWithin(neighbor)
                    && worldMap.isCellEmpty(neighbor)
                    && (!reservedCells.contains(neighbor))) {
                return Optional.of(neighbor);
            }
        }
        return Optional.empty();
    }
}

