package control;

import simulation.Simulation;
import worldmap.WorldMap;

import java.util.Scanner;

public class InputProcessor {

    private static final int HEADER_LINES = 1;
    private static final int FIRST_ROW_NUMBER = 1;
    private final Simulation simulation;
    private final WorldMap worldMap;

    public InputProcessor(Simulation simulation, WorldMap worldMap) {
        this.simulation = simulation;
        this.worldMap = worldMap;
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
        System.out.printf("\u001B[%d;1H", HEADER_LINES + worldMap.getHeight() + FIRST_ROW_NUMBER);
        System.out.println(UserCommand.helpText());
    }

}
