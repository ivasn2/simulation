import control.Cursor;
import control.InputProcessor;
import entity.*;
import simulation.Simulation;
import simulation.SimulationBuilder;
import simulation.action.MoveAllCreaturesAction;
import simulation.action.RemoveDeadCreaturesAction;
import simulation.action.SpawnEntitiesAction;
import simulation.action.ReproduceAction;
import worldmap.WorldMap;

public class Main {
    public static void main(String[] args) {

        WorldMap worldMap = new WorldMap(15, 30);
        Simulation simulation = new SimulationBuilder(worldMap)
                .addInitAction(new SpawnEntitiesAction(Grass.class, Grass::new, 50, 100))
                .addInitAction(new SpawnEntitiesAction(Rock.class, Rock::new, 5, 10))
                .addInitAction(new SpawnEntitiesAction(Tree.class, Tree::new, 5, 10))
                .addInitAction(new SpawnEntitiesAction(Herbivore.class, Herbivore::new, 20, 30))
                .addInitAction(new SpawnEntitiesAction(Predator.class, Predator::new, 1, 3))
                .addTurnAction(new MoveAllCreaturesAction())
                .addTurnAction(new RemoveDeadCreaturesAction())
                .addTurnAction(new SpawnEntitiesAction(Grass.class, Grass::new, 10, 20))
                .addTurnAction(new ReproduceAction(Herbivore::new))
                .addTurnAction(new ReproduceAction(Predator::new))
                .build();

        System.out.print(Cursor.CLEAR_SCREEN);

        InputProcessor inputProcessor = new InputProcessor(simulation, worldMap);

        simulation.startSimulation();
        inputProcessor.showMenu();
        inputProcessor.processInput();
    }
}
