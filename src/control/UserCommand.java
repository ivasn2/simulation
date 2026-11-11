package control;

public enum UserCommand {

    PAUSE("p"),
    RESUME("s"),
    QUIT("q");

    private final String command;
    UserCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static UserCommand getUserCommand(String userInput) {
        for (UserCommand command : UserCommand.values()) {
            if (command.getCommand().equals(userInput)) {
                return command;
            }
        }
        throw new IllegalArgumentException("Not a single letter in the string meets the condition: " + userInput);
    }

    public static String helpText() {
        StringBuilder builder = new StringBuilder();

        for (UserCommand command : UserCommand.values()) {
            if (!builder.isEmpty()) {
                builder.append(" ; ");
            }
            builder.append(command.getCommand());
            builder.append(" - ");
            builder.append(command.name().toLowerCase());
        }
        return builder.toString();
    }
}
