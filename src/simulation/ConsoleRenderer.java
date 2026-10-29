    package simulation;

    import map.Coordinates;
    import map.WorldMap;

    public class ConsoleRenderer {

        public void render(WorldMap worldMap) {
            for (int i = 0; i < worldMap.getRows(); i++) {
                for (int j = 0; j < worldMap.getColumns(); j++) {
                    Coordinates coordinates = new Coordinates(i, j);
                    if (worldMap.isCellEmpty(coordinates)) {
                        System.out.print("⬛");
                    } else {
                        System.out.print(worldMap.getEntity(coordinates).getSymbol());
                    }

                }
                System.out.println();
            }
            System.out.println();
        }

    }
