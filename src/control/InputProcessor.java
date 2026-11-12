package control;

import simulation.Simulation;

import java.util.Scanner;

public class InputProcessor {

    private final Simulation simulation;

    public InputProcessor(Simulation simulation) {
        this.simulation = simulation;
    }

    public void processInput() {

        Scanner scanner = new Scanner(System.in);
        label:
        while (true) {
            UserCommand command;
            String userInput = scanner.nextLine().trim();
            try {
                command = UserCommand.getUserCommand(userInput);
            } catch (IllegalArgumentException e) {
                System.out.println("An error occurred! Invalid value");
                continue;
            }
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

    public void showMenu() {
        System.out.println(UserCommand.helpText());
    }

}
