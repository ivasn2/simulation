package simulation.action;

import entity.Entity;
import entity.Grass;
import map.Coordinates;
import map.WorldMap;

import java.util.Random;

public class ReplenishResourceAction implements Action {

    private Class<? extends Entity> entityClass;
    private int minCount;
    private int maxCount;
    private final Random rnd = new Random();


    public ReplenishResourceAction(Class<? extends Entity> entityClass, int minCount, int maxCount) {
        this.entityClass = entityClass;
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
            for (int i = 0; i < toSpawnCount; i++) {

                try {
                    newEntity = entityClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

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
