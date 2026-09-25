package map;

import entity.Coordinates;
import entity.Entity;

import java.util.HashMap;

public class WorldMap {

    private HashMap<Coordinates, Entity> entityPositions;
    private int length;
    private int width;

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public WorldMap(int length, int width) {
        this.entityPositions = new HashMap<>();
        this.length = length;
        this.width = width;
    }

    public void addEntity(Coordinates coordinates, Entity entity) {
        entityPositions.put(coordinates, entity);
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

    public Entity getAllEntity() {
        return entityPositions.size();
    }
}
