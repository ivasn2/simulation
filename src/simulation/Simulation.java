package simulation;

import map.WorldMap;
import simulation.action.Action;

import java.util.ArrayList;
import java.util.List;


public class Simulation {

    private int turnCount = 0;
    private boolean isRunning;
    private WorldMap worldMap;
    private List<Action> initActions = new ArrayList<>();
    private List<Action> turnActions = new ArrayList<>();
    private ConsoleRenderer consoleRenderer = new ConsoleRenderer();

    public Simulation(int length, int width) {
        this.worldMap = new WorldMap(length, width);
    }

    public void pauseSimulation() {
        isRunning = false;
    }

    public void startSimulation() {

        for (Action action : initActions) {
            action.perform(worldMap);
        }
        isRunning = true;
        Thread simulationThread = new Thread(() -> {
            while (isRunning) {
                nextTurn();
            }
        });
        simulationThread.start();
    }

    public void nextTurn() {
        for (Action action : turnActions) {
            action.perform(worldMap);
        }
        turnCount++;
        consoleRenderer.render(worldMap);
        try {
            Thread.sleep(1100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void addActionToInitActions(Action action) {
        initActions.add(action);
    }

    public void addActionToTurnActions(Action action) {
        turnActions.add(action);
    }
}
