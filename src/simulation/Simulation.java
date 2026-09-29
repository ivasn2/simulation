package simulation;

import map.WorldMap;
import simulation.action.Action;

import java.util.ArrayList;
import java.util.List;


public class Simulation {

    private int turnCount = 0;
    private WorldMap worldMap;
    private List<Action> initActions = new ArrayList<>();
    private List<Action> turnActions = new ArrayList<>();

    public Simulation(int length, int width) {
        this.worldMap = new WorldMap(length, width);
    }

    public void nextTurn() {
        for (Action action : turnActions) {
            action.perform(worldMap);
        }
        turnCount++;
    }
}
