    package simulation;

    import entity.Coordinates;
    import map.WorldMap;

    public class ConsoleRenderer {

        public void render(WorldMap worldMap) {
            for (int i = 0; i < worldMap.getLength(); i++) {
                for (int j = 0; j < worldMap.getWidth(); j++) {
                    Coordinates coordinates = new Coordinates(i, j);
                    if (worldMap.isCellEmpty(coordinates)) {
                        System.out.print("⬛");
                    } else {
                        System.out.print(worldMap.getEntity(coordinates).getSymbol());
                    }
                }
                System.out.println();
            }

        }

    }
