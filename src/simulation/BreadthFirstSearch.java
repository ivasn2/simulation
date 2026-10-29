package simulation;

import entity.Entity;
import map.Coordinates;
import map.WorldMap;

import java.util.*;

public class BreadthFirstSearch {

    public static List<Coordinates> findPath(Coordinates start, WorldMap worldMap, Class<? extends Entity> targetClass) {
        Set<Coordinates> visitedCoordinates = new HashSet<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>();
        Queue<Coordinates> cellsToExplore = new LinkedList<>();

        visitedCoordinates.add(start);
        cellsToExplore.add(start);

        while (!cellsToExplore.isEmpty()) {
            List<Coordinates> cells = new ArrayList<>();
            Coordinates currentCell = cellsToExplore.poll();

            Coordinates cellDown = new Coordinates(currentCell.getX() + 1, currentCell.getY());
            cells.add(cellDown);
            Coordinates cellUp = new Coordinates(currentCell.getX() - 1, currentCell.getY());
            cells.add(cellUp);
            Coordinates cellRight = new Coordinates(currentCell.getX(), currentCell.getY() + 1);
            cells.add(cellRight);
            Coordinates cellLeft = new Coordinates(currentCell.getX(), currentCell.getY() - 1);
            cells.add(cellLeft);

            for (Coordinates neighbor : cells) {
                    if (    (neighbor.getX() >= 0) && (neighbor.getX() <= worldMap.getRows() - 1)
                            && ((neighbor.getY() >= 0) && (neighbor.getY() <= worldMap.getColumns() - 1)

                            && (!visitedCoordinates.contains(neighbor))

                            && ((worldMap.isCellEmpty(neighbor))
                            || (worldMap.getEntity(neighbor).getClass() == targetClass)))) {

                                cellsToExplore.add(neighbor);
                                visitedCoordinates.add(neighbor);
                                cameFrom.put(neighbor, currentCell);

                                if ((!worldMap.isCellEmpty(neighbor)) && (worldMap.getEntity(neighbor).getClass() == targetClass)) {
                                    // TODO: вынести проверку цели в метод
                                    Coordinates current = neighbor;
                                    List<Coordinates> coordinatesList = new ArrayList<>();
                                    coordinatesList.add(neighbor);
                                    while (true) {
                                        Coordinates cameFromCell = cameFrom.get(current);
                                        if (cameFromCell.equals(start)) {
                                            break;
                                        }
                                        else {
                                            current = cameFromCell;
                                            coordinatesList.addFirst(current);
                                        }
                                    }
                                    return coordinatesList;
                                }
                    }
            }
        }
        return Collections.emptyList();
    }
}
