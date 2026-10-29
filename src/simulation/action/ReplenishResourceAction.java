package simulation.action;

import entity.Entity;
import map.Coordinates;
import map.WorldMap;

import java.util.Random;
import java.util.function.Supplier;

public class ReplenishResourceAction implements Action {

    private Class<? extends Entity> entityClass;
    private final Supplier<? extends Entity> entityFactory;
    private int minCount;
    private int maxCount;
    private final Random rnd = new Random();


    public ReplenishResourceAction(Class<? extends Entity> entityClass, Supplier<? extends Entity> entityFactory, int minCount, int maxCount) {
        this.entityClass = entityClass;
        this.entityFactory = entityFactory;
        this.minCount = minCount;
        this.maxCount = maxCount;
    }

    @Override
    public void perform(WorldMap worldMap) {
        int currentCount = 0;
        for (Entity entity : worldMap.getAllEntities()) {
            if (entity.getClass() == entityClass) {
                currentCount++;
            }
        }
        if (currentCount < minCount) {
            int toSpawnCount = maxCount - currentCount;
            Entity newEntity;
            for (int i = 0; ((i < toSpawnCount) && (!worldMap.isFull())); i++) {

                newEntity = entityFactory.get();

                Coordinates freeCell = findEmptyCell(worldMap);
                worldMap.addEntity(freeCell, newEntity);
            }
        }
    }


    public Coordinates findEmptyCell(WorldMap worldMap) {
        Coordinates coordinates = new Coordinates(rnd.nextInt(worldMap.getLength()), rnd.nextInt(worldMap.getWidth()));
        while (!worldMap.isCellEmpty(coordinates)) {
            coordinates = new Coordinates(rnd.nextInt(worldMap.getLength()), rnd.nextInt(worldMap.getWidth()));
        }
        return coordinates;
    }
}
