import simulation.Simulation;
import simulation.action.ArrangeAllEntitiesAction;
import simulation.action.MoveAllCreaturesAction;

public class Main {
    public static void main(String[] args) {

        Simulation simulation = new Simulation(10, 10);
        simulation.addActionToInitActions(new ArrangeAllEntitiesAction());
        simulation.addActionToTurnActions(new MoveAllCreaturesAction());
        simulation.startSimulation();
    }
}