package map;

import entity.Entity;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class WorldMap {

    private final HashMap<Coordinates, Entity> entityPositions;
    private final int rows;
    private final int columns;

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public WorldMap(int rows, int columns) {
        this.entityPositions = new HashMap<>();
        this.rows = rows;
        this.columns = columns;
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

    public Collection<Entity> getAllEntities() {
        return List.copyOf(entityPositions.values());
    }

    public boolean isFull() {
        return entityPositions.size() == getRows() * getColumns();
    }
}
