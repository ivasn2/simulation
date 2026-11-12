import control.InputProcessor;
import entity.*;
import simulation.Simulation;
import simulation.action.MoveAllCreaturesAction;
import simulation.action.RemoveDeadCreaturesAction;
import simulation.action.SpawnEntitiesAction;
import simulation.action.ReproduceAction;
import worldmap.WorldMap;

public class Main {
    public static void main(String[] args) {

        WorldMap worldMap = new WorldMap(15, 30);
        Simulation simulation = new Simulation(worldMap);
        InputProcessor inputProcessor = new InputProcessor(simulation, worldMap);

        simulation.addInitAction(new SpawnEntitiesAction(Grass.class, Grass::new, 50, 100));
        simulation.addInitAction(new SpawnEntitiesAction(Rock.class, Rock::new, 5, 10));
        simulation.addInitAction(new SpawnEntitiesAction(Tree.class, Tree::new, 5, 10));
        simulation.addInitAction(new SpawnEntitiesAction(Herbivore.class, Herbivore::new, 20, 30));
        simulation.addInitAction(new SpawnEntitiesAction(Predator.class, Predator::new, 1, 3));

        simulation.addActionToTurnActions(new MoveAllCreaturesAction());
        simulation.addActionToTurnActions(new RemoveDeadCreaturesAction());
        simulation.addActionToTurnActions(new SpawnEntitiesAction(Grass.class, Grass::new, 10, 20));
        simulation.addActionToTurnActions(new ReproduceAction(Herbivore::new));
        simulation.addActionToTurnActions(new ReproduceAction(Predator::new));

        System.out.print("\u001B[2J");
        simulation.startSimulation();

        inputProcessor.showMenu();
        inputProcessor.processInput();
    }
}
