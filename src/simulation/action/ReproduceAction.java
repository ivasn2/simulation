package simulation.action;

import entity.Creature;
import entity.Entity;
import map.Coordinates;
import map.WorldMap;

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
        Coordinates parentCoord = parent.getCoordinates();

        List<Coordinates> neighbors = new ArrayList<>();
        neighbors.add(new Coordinates(parentCoord.getX() + 1, parentCoord.getY())); // Право
        neighbors.add(new Coordinates(parentCoord.getX() - 1, parentCoord.getY())); // Лево
        neighbors.add(new Coordinates(parentCoord.getX(), parentCoord.getY() + 1)); // Верх
        neighbors.add(new Coordinates(parentCoord.getX(), parentCoord.getY() - 1)); // Низ

        for (Coordinates candidate : neighbors) {
            if ( (candidate.getX() >= 0)
                    && (candidate.getX() <= worldMap.getLength() - 1)
                    && (candidate.getY() >= 0 && candidate.getY() <= worldMap.getWidth() - 1)
                    && (!worldMap.isCellEmpty(candidate))
                    && (worldMap.getEntity(candidate).getClass() == creatureClass)) {
                return (Creature) worldMap.getEntity(candidate);
            }
        }
        return null;
    }

    private static Coordinates findEmptyNeighborCell(Coordinates parent1, WorldMap worldMap, Set<Coordinates> reservedCells) {
        List<Coordinates> neighbors = new ArrayList<>();

        neighbors.add(new Coordinates(parent1.getX() + 1, parent1.getY()));
        neighbors.add(new Coordinates(parent1.getX() - 1, parent1.getY()));
        neighbors.add(new Coordinates(parent1.getX(), parent1.getY() + 1));
        neighbors.add(new Coordinates(parent1.getX(), parent1.getY() - 1));

        for (Coordinates neighbor : neighbors) {
            if ( (neighbor.getX() >= 0)
                    && (neighbor.getX() <= worldMap.getLength() - 1)
                    && (neighbor.getY() >= 0 && neighbor.getY() <= worldMap.getWidth() - 1)
                    && (worldMap.isCellEmpty(neighbor))
                    && (!reservedCells.contains(neighbor))) {
                return neighbor;
                }
            }
        return null;
        }
    }

