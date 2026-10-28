package simulation;

import entity.Entity;
import map.Coordinates;
import map.WorldMap;

import java.util.*;

public class BreadthFirstSearch {

    public static List<Coordinates> findPath(Coordinates start, WorldMap worldMap, Class<? extends Entity> targetClass) {
        Set<Coordinates> visitedCoordinates = new HashSet<>();
        Map<Coordinates, Coordinates> arrivedCoordinates = new HashMap<>();
        Queue<Coordinates> cellsToExplore = new LinkedList<>();

        visitedCoordinates.add(start);
        cellsToExplore.add(start);

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
                            || (worldMap.getEntity(neighbor).getClass() == targetClass)))) {

                                cellsToExplore.add(neighbor);
                                visitedCoordinates.add(neighbor);
                                arrivedCoordinates.put(neighbor, currentCell);

                                if ((!worldMap.isCellEmpty(neighbor)) && (worldMap.getEntity(neighbor).getClass() == targetClass)) {
                                    // TODO: вынести проверку цели в метод
                                    Coordinates current = neighbor;
                                    List<Coordinates> coordinatesList = new ArrayList<>();
                                    coordinatesList.add(neighbor);
                                    while (true) {
                                        Coordinates previous = arrivedCoordinates.get(current);
                                        if (previous.equals(start)) {
                                            break;
                                        }
                                        else {
                                            current = previous;
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
