package simulation.action;

import entity.*;
import map.Coordinates;
import map.WorldMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrangeAllEntitiesAction implements Action {

    Random rnd = new Random();


    @Override
    public void perform(WorldMap worldMap) {

        Coordinates coordinates = new Coordinates(rnd.nextInt(worldMap.getLength()), rnd.nextInt(worldMap.getWidth()));
        List<Entity> entitiesOnMap = new ArrayList<>();

        Entity grass = new Grass();
        Entity rock = new Rock();
        Entity tree = new Tree();
        Entity herbivore = new Herbivore("\uD83E\uDECE", 100, 25);
        Predator predator = new Predator("\uD83D\uDC3B", 150, 15, 25);

        entitiesOnMap.add(grass);
        entitiesOnMap.add(rock);
        entitiesOnMap.add(tree);
        entitiesOnMap.add(herbivore);
        entitiesOnMap.add(predator);

        for (Entity entity : entitiesOnMap) {
            // Цикл для того, если у нас клетка которую задали уже занята - то ищется новая рандомная
            while (!worldMap.isCellEmpty(coordinates)) {
                coordinates = new Coordinates(rnd.nextInt(worldMap.getLength()), rnd.nextInt(worldMap.getWidth()));
            }
            worldMap.addEntity(coordinates, entity);
        }

    }



    public ArrangeAllEntitiesAction() {
    }
}
