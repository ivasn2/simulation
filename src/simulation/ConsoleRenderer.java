package simulation;

import worldmap.Coordinates;
import worldmap.WorldMap;

public class ConsoleRenderer {

    public void render(WorldMap worldMap, int turnCount) {

        System.out.print("\u001B[H");
        System.out.println("=== Ход №" + turnCount + " ===");
        for (int i = 0; i < worldMap.getHeight(); i++) {
            for (int j = 0; j < worldMap.getWidth(); j++) {
                Coordinates coordinates = new Coordinates(i, j);
                if (worldMap.isCellEmpty(coordinates)) {
                    System.out.print("\uD83D\uDFEB");
                } else {
                    System.out.print(worldMap.getEntity(coordinates).getSymbol());
                }

            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Commands: p - pause, s - resume, q - quit");
    }

}
