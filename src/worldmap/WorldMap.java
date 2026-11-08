package worldmap;

import entity.Creature;
import entity.Entity;


import java.util.*;

public class WorldMap {

    private final Map<Coordinates, Entity> entityPositions;
    private final int height;
    private final int width;

    public WorldMap(int height, int width) {
        this.entityPositions = new HashMap<>();
        this.height = height;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        validate(coordinates);
        if (!isCellEmpty(coordinates)) {
            throw new IllegalStateException(
                    String.format("Координата %s уже занята", coordinates));
        }

        if (entity instanceof Creature) {
            Creature creature = (Creature) entity;
            creature.setCoordinates(coordinates);
        }
        entityPositions.put(coordinates, entity);
    }

    public void removeEntity(Coordinates coordinates) {
        validate(coordinates);
        entityPositions.remove(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
        validate(coordinates);
        if (entityPositions.get(coordinates) == null) {
            throw new NoSuchElementException("На данной клетке нет существа");
        }
        return entityPositions.get(coordinates);
    }

    public boolean isCellEmpty(Coordinates coordinates) {
        return !entityPositions.containsKey(coordinates);
    }

    public boolean isWithin(Coordinates coordinates) {
        return (coordinates.x() >= 0)
                && (coordinates.x() <= getHeight() - 1)
                && (coordinates.y() >= 0 && coordinates.y() <= getWidth() - 1);
    }

    public Collection<Entity> getAllEntities() {
        return List.copyOf(entityPositions.values());
    }

    public boolean isFull() {
        return entityPositions.size() == getHeight() * getWidth();
    }

    private void validate(Coordinates coordinates) {
        if (!isWithin(coordinates)) {
            throw new IllegalArgumentException(
                    String.format("Координата %s вне карты %dx%d", coordinates, height, width));
        }
    }
}
