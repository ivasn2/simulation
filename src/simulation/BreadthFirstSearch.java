package simulation;

import entity.Entity;
import worldmap.Coordinates;
import worldmap.WorldMap;

import java.util.*;

public class BreadthFirstSearch {

    public static List<Coordinates> findPath(Coordinates start, WorldMap worldMap, Class<? extends Entity> targetClass) {
        Set<Coordinates> visitedCoordinates = new HashSet<>();
        Map<Coordinates, Coordinates> cameFrom = new HashMap<>();
        Queue<Coordinates> cellsToExplore = new LinkedList<>();

        visitedCoordinates.add(start);
        cellsToExplore.add(start);

        while (!cellsToExplore.isEmpty()) {

            Coordinates currentCell = cellsToExplore.poll();
            List<Coordinates> neighborCells = NeighborsFinder.neighborCells(currentCell);

            for (Coordinates neighbor : neighborCells) {

                if ((worldMap.isWithin(neighbor))
                        && (!visitedCoordinates.contains(neighbor))
                        && ((worldMap.isCellEmpty(neighbor))
                        || (isTargetCell(neighbor, targetClass, worldMap)))) {

                    cellsToExplore.add(neighbor);
                    visitedCoordinates.add(neighbor);
                    cameFrom.put(neighbor, currentCell);

                    if (isTargetCell(neighbor, targetClass, worldMap)) {

                        Coordinates current = neighbor;
                        List<Coordinates> coordinatesList = new ArrayList<>();

                        coordinatesList.add(neighbor);

                        while (true) {
                            Coordinates previousCell = cameFrom.get(current);
                            if (previousCell.equals(start)) {
                                break;
                            } else {
                                current = previousCell;
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

    private static boolean isTargetCell(Coordinates neighborCell, Class<? extends Entity> targetClassEntity, WorldMap worldMap) {
        return ((!worldMap.isCellEmpty(neighborCell)) && (worldMap.getEntity(neighborCell).getClass() == targetClassEntity));
    }
}
