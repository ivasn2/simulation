import entity.*;
import simulation.Simulation;
import simulation.action.MoveAllCreaturesAction;
import simulation.action.SpawnEntitiesAction;
import simulation.action.ReproduceAction;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        final String PAUSE = "p";
        final String RESUME = "s";
        final String QUIT = "q";

        Simulation simulation = new Simulation(15, 30);

        simulation.addActionToInitActions(new SpawnEntitiesAction(Grass.class, Grass::new, 20, 40));
        simulation.addActionToInitActions(new SpawnEntitiesAction(Rock.class, Rock::new, 5, 10));
        simulation.addActionToInitActions(new SpawnEntitiesAction(Tree.class, Tree::new, 5, 10));
        simulation.addActionToInitActions(new SpawnEntitiesAction(Herbivore.class, Herbivore::new, 5, 10));
        simulation.addActionToInitActions(new SpawnEntitiesAction(Predator.class, Predator::new, 5, 10));

        simulation.addActionToTurnActions(new MoveAllCreaturesAction());
        simulation.addActionToTurnActions(new SpawnEntitiesAction(Grass.class, Grass::new, 5, 10));
        simulation.addActionToTurnActions(new ReproduceAction(Herbivore.class));
        simulation.addActionToTurnActions(new ReproduceAction(Predator.class));

        simulation.startSimulation();

        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("Commands: p - pause, s - resume, q - quit");
        while (scanner.hasNextLine()) {
            command = scanner.nextLine().trim();
            if (command.equals(PAUSE)) {
                simulation.pauseSimulation();
            } else if (command.equals(RESUME)) {
                simulation.resumeSimulation();
            } else if (command.equals(QUIT)) {
                simulation.pauseSimulation();
                break;
            }
        }
    }
}