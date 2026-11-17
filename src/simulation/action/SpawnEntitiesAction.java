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
    private final int targetCount;
    private final Random random = new Random();

    public SpawnEntitiesAction(Class<? extends Entity> entityClass, Supplier<? extends Entity> entityFactory, int refillBelow, int targetCount) {
        this.entityClass = entityClass;
        this.entityFactory = entityFactory;
        this.refillBelow = refillBelow;
        this.targetCount = targetCount;
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
            int spawnCount = targetCount - currentCount;
            for (int i = 0; i < spawnCount; i++) {
                if (worldMap.isFull()) {
                    break;
                }
                Entity newEntity = entityFactory.get();
                Coordinates freeCell = findEmptyCell(worldMap);
                worldMap.addEntity(freeCell, newEntity);
            }
        }
    }

    private Coordinates findEmptyCell(WorldMap worldMap) {
        Coordinates coordinates;
        do {
            coordinates = new Coordinates(random.nextInt(worldMap.getWidth()), random.nextInt(worldMap.getHeight()));
        } while (!worldMap.isCellEmpty(coordinates));
        return coordinates;
    }
}
