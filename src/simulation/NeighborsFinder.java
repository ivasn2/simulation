package simulation;

import worldmap.Coordinates;

import java.util.ArrayList;
import java.util.List;

public final class NeighborsFinder {
    public static List<Coordinates> neighborCells(Coordinates cell) {
        List<Coordinates> neighbors = new ArrayList<>();
        neighbors.add(new Coordinates(cell.x() + 1, cell.y()));
        neighbors.add(new Coordinates(cell.x() - 1, cell.y()));
        neighbors.add(new Coordinates(cell.x(), cell.y() + 1));
        neighbors.add(new Coordinates(cell.x(), cell.y() - 1));
        return neighbors;
    }

    private NeighborsFinder() {
    }
}
