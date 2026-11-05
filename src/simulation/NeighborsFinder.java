package simulation;

import worldmap.Coordinates;

import java.util.ArrayList;
import java.util.List;

public final class NeighborsFinder {
    public static List<Coordinates> neighborCells(Coordinates cell) {
        List<Coordinates> neighbors = new ArrayList<>();
        neighbors.add(new Coordinates(cell.x() + 1, cell.y())); // Вниз
        neighbors.add(new Coordinates(cell.x() - 1, cell.y())); // Вверх
        neighbors.add(new Coordinates(cell.x(), cell.y() + 1)); // Вправо
        neighbors.add(new Coordinates(cell.x(), cell.y() - 1)); // Влево
        return neighbors;
    }

    private NeighborsFinder() {
    }
}
