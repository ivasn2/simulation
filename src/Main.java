import entity.*;
import simulation.Simulation;
import simulation.action.MoveAllCreaturesAction;
import simulation.action.ReplenishResourceAction;

public class Main {
    public static void main(String[] args) {
        Simulation simulation = new Simulation(15, 30);
        simulation.addActionToInitActions(new ReplenishResourceAction(Grass.class, 5, 10));
        simulation.addActionToInitActions(new ReplenishResourceAction(Rock.class, 5, 10));
        simulation.addActionToInitActions(new ReplenishResourceAction(Tree.class, 5, 10));
        simulation.addActionToInitActions(new ReplenishResourceAction(Herbivore.class, 5, 10));
        simulation.addActionToInitActions(new ReplenishResourceAction(Predator.class, 5, 10));
        simulation.addActionToTurnActions(new MoveAllCreaturesAction());
        simulation.addActionToTurnActions(new ReplenishResourceAction(Grass.class, 5, 10));
        simulation.startSimulation();
    }
}