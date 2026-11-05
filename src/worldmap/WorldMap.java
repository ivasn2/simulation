package worldmap;

import entity.Entity;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldMap {

    private final Map<Coordinates, Entity> entityPositions;
    private final int height;
    private final int width;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public WorldMap(int height, int width) {
        this.entityPositions = new HashMap<>();
        this.height = height;
        this.width = width;
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        if (isCellEmpty(coordinates)) {
            entity.setCoordinates(coordinates);
            entityPositions.put(coordinates, entity);
        }
    }

    public void removeEntity(Coordinates coordinates) {
        entityPositions.remove(coordinates);
    }

    public Entity getEntity(Coordinates coordinates) {
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

    public void moveEntity(Coordinates destination, Entity entityToMove) {
        removeEntity(entityToMove.getCoordinates());
        addEntity(destination, entityToMove);
    }

    public boolean isCellContainsRelative(Coordinates neighborCell, Class<? extends Entity> targetClassEntity) {
        return ((!isCellEmpty(neighborCell)) && (getEntity(neighborCell).getClass() == targetClassEntity));
    }
}
