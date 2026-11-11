package simulation;

import control.UserCommand;
import entity.*;
import worldmap.Coordinates;
import worldmap.WorldMap;

import java.util.HashMap;
import java.util.Map;

public class ConsoleRenderer {

    private final Map<Class<? extends Entity>, String> symbols;

    public ConsoleRenderer() {
        this.symbols = new HashMap<>();
        symbols.put(Grass.class, "\uD83C\uDF3F");
        symbols.put(Rock.class, "\uD83E\uDEA8");
        symbols.put(Tree.class, "\uD83C\uDF33");
        symbols.put(Herbivore.class, "\uD83E\uDD8C");
        symbols.put(Predator.class, "\uD83D\uDC3A");
    }

    public void render(WorldMap worldMap, int turnCount) {
        System.out.print("\u001B[H");
        System.out.printf("=== Ход №%d ===\n", turnCount);
        for (int i = 0; i < worldMap.getHeight(); i++) {
            for (int j = 0; j < worldMap.getWidth(); j++) {
                Coordinates coordinates = new Coordinates(i, j);
                if (worldMap.isCellEmpty(coordinates)) {
                    System.out.print("\uD83D\uDFEB");
                } else {
                    System.out.print(symbols.get(worldMap.getEntity(coordinates).getClass()));
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(UserCommand.helpText());
    }
}
