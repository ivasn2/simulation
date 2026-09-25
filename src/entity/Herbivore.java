package entity;

public class Herbivore extends Creature {

    public Herbivore(String symbol, int hp, int speed) {
        super(symbol, hp, speed);
    }

    @Override
    void makeMove() {
        // Здесь будет алгоритм поиска пути
    }
}
