package control;

public final class Cursor {

    public static final String CLEAR_SCREEN = "\u001B[2J";
    public static final String MOVE_HOME = "\u001B[H";
    public static final String MOVE_TO_LINE_FORMAT = "\u001B[%d;1H";

    private Cursor() {}
}