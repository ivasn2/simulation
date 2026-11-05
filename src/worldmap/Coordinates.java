package worldmap;

import java.util.ArrayList;
import java.util.List;

public record Coordinates(int x, int y) {
    public List<Coordinates> neighborCells() {
        List<Coordinates> neighbors = new ArrayList<>();
        neighbors.add(new Coordinates(x() + 1, y())); // Вниз
        neighbors.add(new Coordinates(x() - 1, y())); // Вверх
        neighbors.add(new Coordinates(x(), y() + 1)); // Вправо
        neighbors.add(new Coordinates(x(), y() - 1)); // Влево
        return neighbors;
    }
}
