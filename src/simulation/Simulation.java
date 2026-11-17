package simulation;

import control.Cursor;
import worldmap.WorldMap;
import simulation.action.Action;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


public class Simulation {

    public static final int HEADER_LINES = 1;
    private static final String TURN_HEADER_FORMAT = "=== Ход №%d ===\n";
    private static final long TURN_DELAY_MS = 1100;
    private int turnCount = 0;
    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> turnTask;
    private final WorldMap worldMap;
    private final List<Action> initActions;
    private final List<Action> turnActions;
    private final ConsoleRenderer consoleRenderer = new ConsoleRenderer();

    Simulation(WorldMap worldMap, List<Action> initActions, List<Action> turnActions) {
        this.worldMap = worldMap;
        this.initActions = List.copyOf(initActions);
        this.turnActions = List.copyOf(turnActions);
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

        System.out.print(Cursor.MOVE_HOME);

        System.out.printf(TURN_HEADER_FORMAT, turnCount);

        consoleRenderer.render(worldMap);
    }

    public void resumeSimulation() {
        if ((turnTask == null) || (turnTask.isDone())) {
            turnTask = executor.scheduleWithFixedDelay(this::nextTurn, 0, TURN_DELAY_MS, TimeUnit.MILLISECONDS);
        }
    }

    public void quitSimulation() {
        if (turnTask != null) {
            turnTask.cancel(false);
        }
        executor.shutdownNow();
    }
}
