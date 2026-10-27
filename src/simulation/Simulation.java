package simulation;

import map.WorldMap;
import simulation.action.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class Simulation {

    private int turnCount = 0;
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> turnTask;
    private static final long TURN_DELAY_MS = 1100;

    private WorldMap worldMap;
    private List<Action> initActions = new ArrayList<>();
    private List<Action> turnActions = new ArrayList<>();
    private ConsoleRenderer consoleRenderer = new ConsoleRenderer();

    public Simulation(int length, int width) {
        this.worldMap = new WorldMap(length, width);
    }

    public void pauseSimulation() {
        if (turnTask != null) {
            turnTask.cancel(false);
        }
    }

    public void startSimulation() {
        for (Action action : initActions) {
            action.perform(worldMap);
        }
        resumeSimulation();
    }

    public void nextTurn() {
        for (Action action : turnActions) {
            action.perform(worldMap);
        }
        turnCount++;
        consoleRenderer.render(worldMap);
    }

    public void addActionToInitActions(Action action) {
        initActions.add(action);
    }

    public void addActionToTurnActions(Action action) {
        turnActions.add(action);
    }

    public void resumeSimulation() {
        if ((turnTask == null) || (turnTask.isDone())) {
            turnTask = executor.scheduleWithFixedDelay(this::nextTurn, 0, TURN_DELAY_MS, TimeUnit.MILLISECONDS);
        }
    }
}
