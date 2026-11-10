import entity.*;
import simulation.Simulation;
import simulation.action.MoveAllCreaturesAction;
import simulation.action.RemoveDeadCreaturesAction;
import simulation.action.SpawnEntitiesAction;
import simulation.action.ReproduceAction;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final String PAUSE = "p";
        final String RESUME = "s";
        final String QUIT = "q";

        Simulation simulation = new Simulation(15, 30);

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

        Scanner scanner = new Scanner(System.in);
        String command;
        label:
        while (scanner.hasNextLine()) {
            command = scanner.nextLine().trim();
            switch (command) {
                case PAUSE:
                    simulation.pauseSimulation();
                    break;
                case RESUME:
                    simulation.resumeSimulation();
                    break;
                case QUIT:
                    simulation.quitSimulation();
                    break label;
            }
        }
    }
}