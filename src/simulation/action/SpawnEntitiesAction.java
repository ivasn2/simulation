package simulation.action;

import entity.Entity;
import map.Coordinates;
import map.WorldMap;

import java.util.Random;
import java.util.function.Supplier;

public class SpawnEntitiesAction implements Action {

    private Class<? extends Entity> entityClass;
    private final Supplier<? extends Entity> entityFactory;
    private int refillBelow;
    private int refillTo;
    private final Random rnd = new Random();


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
            int toSpawnCount = refillTo - currentCount;
            Entity newEntity;
            for (int i = 0; ((i < toSpawnCount) && (!worldMap.isFull())); i++) {

                newEntity = entityFactory.get();

                Coordinates freeCell = findEmptyCell(worldMap);
                worldMap.addEntity(freeCell, newEntity);
            }
        }
    }


    public Coordinates findEmptyCell(WorldMap worldMap) {
        Coordinates coordinates = new Coordinates(rnd.nextInt(worldMap.getRows()), rnd.nextInt(worldMap.getColumns()));
        while (!worldMap.isCellEmpty(coordinates)) {
            coordinates = new Coordinates(rnd.nextInt(worldMap.getRows()), rnd.nextInt(worldMap.getColumns()));
        }
        return coordinates;
    }
}
