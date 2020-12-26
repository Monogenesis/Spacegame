package spacegame.controller.commands;

import java.util.ArrayList;

public interface Command {

    public static ArrayList<Command> allCommands = new ArrayList<>();

    void execute();

    void undo();
}
