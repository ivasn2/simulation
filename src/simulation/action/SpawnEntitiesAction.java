package simulation.action;

import entity.Entity;
import worldmap.Coordinates;
import worldmap.WorldMap;

import java.util.Random;
import java.util.function.Supplier;

public class SpawnEntitiesAction implements Action {

    private final Class<? extends Entity> entityClass;
    private final Supplier<? extends Entity> entityFactory;
    private final int refillBelow;
    private final int refillTo;
    private final Random random = new Random();


    public SpawnEntitiesAction(Class<? extends Entity> entityClass, Supplier<? extends Entity> entityFactory, int refillBelow, int refillTo) {
        this.entityClass = entityClass;
        this.entityFactory = entityFactory;
        this.refillBelow = refillBelow;
        this.refillTo = refillTo;
    }

    @Override
    public void perform(WorldMap worldMap) {
        int currentCount = 0;
        for (Entity entity : worldMap.getAllEntities()) {
            if (entity.getClass() == entityClass) {
                currentCount++;
            }
        }
        if (currentCount < refillBelow) {
            int spawnCount = refillTo - currentCount;
            Entity newEntity;
            for (int i = 0; ((i < toSpawnCount) && (!worldMap.isFull())); i++) {

                newEntity = entityFactory.get();

                Coordinates freeCell = findEmptyCell(worldMap);
                worldMap.addEntity(freeCell, newEntity);
            }
        }
    }


    public Coordinates findEmptyCell(WorldMap worldMap) {
        Coordinates coordinates = new Coordinates(random.nextInt(worldMap.getHeight()), random.nextInt(worldMap.getWidth()));
        while (!worldMap.isCellEmpty(coordinates)) {
            coordinates = new Coordinates(random.nextInt(worldMap.getHeight()), random.nextInt(worldMap.getWidth()));
        }
        return coordinates;
    }
}
