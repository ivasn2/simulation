package simulation;

import entity.Entity;
import map.Coordinates;
import map.WorldMap;

import java.util.*;

public class BreadthFirstSearch {

    public Coordinates breadthFirstSearch(Coordinates coordinates, WorldMap worldMap, Entity entity) {
        Set<Coordinates> visitedCoordinates = new HashSet<>();
        Map<Coordinates, Coordinates> arrivedCoordinates = new HashMap<>();
        Queue<Coordinates> cellsToExplore = new LinkedList<>();


        visitedCoordinates.add(coordinates);
        cellsToExplore.add(coordinates);

        while (!cellsToExplore.isEmpty()) {
            List<Coordinates> cells = new ArrayList<>();
            Coordinates currentCell = cellsToExplore.poll();
            Coordinates cellRight = new Coordinates(currentCell.getX() + 1, currentCell.getY());
            cells.add(cellRight);
            Coordinates cellLeft = new Coordinates(currentCell.getX() - 1, currentCell.getY());
            cells.add(cellLeft);
            Coordinates cellUp = new Coordinates(currentCell.getX(), currentCell.getY() + 1);
            cells.add(cellUp);
            Coordinates cellDown = new Coordinates(currentCell.getX(), currentCell.getY() - 1);
            cells.add(cellDown);

            for (Coordinates neighbor : cells) {
                    if (    (neighbor.getX() >= 0) && (neighbor.getX() <= worldMap.getLength() - 1)
                            && ((neighbor.getY() >= 0) && (neighbor.getY() <= worldMap.getWidth() - 1)

                            && (!visitedCoordinates.contains(neighbor))

                            && ((worldMap.isCellEmpty(neighbor))
                            || (worldMap.getEntity(neighbor).getClass() == entity.getClass())))) {

                                cellsToExplore.add(neighbor);
                                visitedCoordinates.add(neighbor);
                                arrivedCoordinates.put(neighbor, currentCell);

                                if ((!worldMap.isCellEmpty(neighbor)) && (worldMap.getEntity(neighbor).getClass() == entity.getClass())) {
                                    // TODO: вынести проверку цели в метод
                                    Coordinates current = neighbor;
                                    while (true) {
                                        Coordinates previous = arrivedCoordinates.get(current);
                                        if (previous.equals(coordinates)) {
                                            break;
                                        }
                                        else {
                                            current = previous;
                                        }

                                    }
                                    return current;
                                }
                    }
            }
        }
        return null;
    }
}
