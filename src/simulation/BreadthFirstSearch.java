package simulation;

import entity.Entity;
import worldmap.Coordinates;
import worldmap.WorldMap;

import java.util.*;

public final class BreadthFirstSearch {

    private BreadthFirstSearch() {
    }

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
                        return assemblePath(cameFrom, start, neighbor);
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    private static boolean isTargetCell(Coordinates neighborCell, Class<? extends Entity> targetClassEntity, WorldMap worldMap) {
        return ((!worldMap.isCellEmpty(neighborCell)) && (worldMap.getEntity(neighborCell).getClass() == targetClassEntity));
    }

    private static List<Coordinates> assemblePath(Map<Coordinates, Coordinates> cameFrom, Coordinates start, Coordinates targetCell) {

        Coordinates current = targetCell;
        List<Coordinates> path = new ArrayList<>();
        path.add(targetCell);

        while (true) {
            Coordinates previousCell = cameFrom.get(current);
            if (previousCell.equals(start)) {
                break;
            } else {
                current = previousCell;
                path.addFirst(current);
            }
        }
        return path;
    }
}
