import entity.*;
import simulation.Simulation;
import simulation.action.MoveAllCreaturesAction;
import simulation.action.ReplenishResourceAction;
import simulation.action.ReproduceAction;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Simulation simulation = new Simulation(15, 30);

        simulation.addActionToInitActions(new ReplenishResourceAction(Grass.class, Grass::new, 20, 40));
        simulation.addActionToInitActions(new ReplenishResourceAction(Rock.class, Rock::new, 5, 10));
        simulation.addActionToInitActions(new ReplenishResourceAction(Tree.class, Tree::new, 5, 10));
        simulation.addActionToInitActions(new ReplenishResourceAction(Herbivore.class, Herbivore::new, 5, 10));
        simulation.addActionToInitActions(new ReplenishResourceAction(Predator.class, Predator::new, 5, 10));

        simulation.addActionToTurnActions(new MoveAllCreaturesAction());
        simulation.addActionToTurnActions(new ReplenishResourceAction(Grass.class, Grass::new, 5, 10));
        simulation.addActionToTurnActions(new ReproduceAction(Herbivore.class));
        simulation.addActionToTurnActions(new ReproduceAction(Predator.class));

        simulation.startSimulation();

        Scanner scanner = new Scanner(System.in);
        String command;
        while (true) {
            command = scanner.nextLine();
            if (command.equals("p")) {
                simulation.pauseSimulation();
            } else if (command.equals("s")) {
                simulation.resumeSimulation();
            }
        }
    }
}