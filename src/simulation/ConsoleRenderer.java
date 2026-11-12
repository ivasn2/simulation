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

    public void render(WorldMap worldMap) {

        for (int y = 0; y < worldMap.getHeight(); y++) {

            for (int x = 0; x < worldMap.getWidth(); x++) {

                Coordinates coordinates = new Coordinates(x, y);

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
