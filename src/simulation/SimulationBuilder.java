package simulation;

import simulation.action.Action;
import worldmap.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class SimulationBuilder {

    private final WorldMap worldMap;
    private final List<Action> initActions = new ArrayList<>();
    private final List<Action> turnActions = new ArrayList<>();

    public SimulationBuilder(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public Simulation build() {
        return new Simulation(worldMap, initActions, turnActions);
    }

    public SimulationBuilder addInitAction(Action action) {
        initActions.add(action);
        return this;
    }

    public SimulationBuilder addTurnAction(Action action) {
        turnActions.add(action);
        return this;
    }
}
