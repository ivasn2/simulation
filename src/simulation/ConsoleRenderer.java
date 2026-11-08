package simulation;

import entity.*;
import worldmap.Coordinates;
import worldmap.WorldMap;

public class ConsoleRenderer {

    public void render(WorldMap worldMap, int turnCount) {

        System.out.print("\u001B[H");
        System.out.printf("=== Ход №%d ===\n", turnCount);
        for (int i = 0; i < worldMap.getHeight(); i++) {
            for (int j = 0; j < worldMap.getWidth(); j++) {
                Coordinates coordinates = new Coordinates(i, j);
                if (worldMap.isCellEmpty(coordinates)) {
                    System.out.print("\uD83D\uDFEB");
                } else {
                    if (worldMap.getEntity(coordinates) instanceof Grass) {
                        System.out.print("\uD83C\uDF3F");
                    }
                    if (worldMap.getEntity(coordinates) instanceof Rock) {
                        System.out.print("\uD83E\uDEA8");
                    }
                    if (worldMap.getEntity(coordinates) instanceof Tree) {
                        System.out.print("\uD83C\uDF33");
                    }
                    if (worldMap.getEntity(coordinates) instanceof Herbivore) {
                        System.out.print("\uD83E\uDD8C");
                    }
                    if (worldMap.getEntity(coordinates) instanceof Predator) {
                        System.out.print("\uD83D\uDC3A");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Commands: p - pause, s - resume, q - quit");
    }

}
