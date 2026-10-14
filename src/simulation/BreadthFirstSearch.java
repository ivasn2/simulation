package simulation;

import entity.Entity;
import map.Coordinates;
import map.WorldMap;

import java.util.*;

public class BreadthFirstSearch {

    public Coordinates breadthFirstSearch(Coordinates coordinates, WorldMap worldMap, Entity entity) {
        Set<Coordinates> visitedCoordinates = new HashSet<>();
        Queue<Coordinates> cellsToExplore = new LinkedList<>();


        visitedCoordinates.add(coordinates);
        cellsToExplore.add(coordinates);

        while (!cellsToExplore.isEmpty()) {
            List<Coordinates> cells = new ArrayList<>();
            Coordinates cell = cellsToExplore.poll();
            Coordinates cellRight = new Coordinates(cell.getX() + 1, cell.getY());
            cells.add(cellRight);
            Coordinates cellLeft = new Coordinates(cell.getX() - 1, cell.getY());
            cells.add(cellLeft);
            Coordinates cellUp = new Coordinates(cell.getX(), cell.getY() + 1);
            cells.add(cellUp);
            Coordinates cellDown = new Coordinates(cell.getX(), cell.getY() - 1);
            cells.add(cellDown);

            for (Coordinates celka : cells) {
                    if ((celka.getX() >= 0) && (celka.getX() <= worldMap.getLength() - 1)
                            && ((celka.getY() >= 0) && (celka.getY() <= worldMap.getWidth() - 1)
                            && (!visitedCoordinates.contains(celka))
                            && ((worldMap.isCellEmpty(celka))
                            || (worldMap.getEntity(celka).getClass() == entity.getClass())))) {
                                cellsToExplore.add(celka);
                                visitedCoordinates.add(celka);

                                if ((!worldMap.isCellEmpty(celka)) &&  (worldMap.getEntity(celka).getClass() == entity.getClass())) {
                                    // TODO: вынести проверку цели в метод
                                    return celka;
                                }
                    }
            }
        }
        return null;
    }
}
