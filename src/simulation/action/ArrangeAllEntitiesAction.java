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

        Coordinates coordinates;

        for (int n = 0; n < 3; n++) {

            Entity grass = new Grass();
            coordinates = findEmptyCell(worldMap);
            worldMap.addEntity(coordinates, grass);

            Entity rock = new Rock();
            coordinates = findEmptyCell(worldMap);
            worldMap.addEntity(coordinates, rock);

            Entity tree = new Tree();
            coordinates = findEmptyCell(worldMap);
            worldMap.addEntity(coordinates, tree);

            Entity herbivore = new Herbivore("\uD83E\uDECE", 100, 25);
            coordinates = findEmptyCell(worldMap);
            worldMap.addEntity(coordinates, herbivore);

            Predator predator = new Predator("\uD83D\uDC3B", 150, 15, 25);
            coordinates = findEmptyCell(worldMap);
            worldMap.addEntity(coordinates, predator);
        }
    }

    public ArrangeAllEntitiesAction() {}

    public Coordinates findEmptyCell(WorldMap worldMap) {
        Coordinates coordinates = new Coordinates(rnd.nextInt(worldMap.getLength()), rnd.nextInt(worldMap.getWidth()));
        while (!worldMap.isCellEmpty(coordinates)) {
            coordinates = new Coordinates(rnd.nextInt(worldMap.getLength()), rnd.nextInt(worldMap.getWidth()));
        }
        return coordinates;
    }
}
