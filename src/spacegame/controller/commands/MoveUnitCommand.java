package spacegame.controller.commands;

import spacegame.gameobjects.GameObject;

public class MoveUnitCommand implements Command {

    private GameObject unit;
    private int x;
    private int y;
    private int xBefore;
    private int yBefore;

    public MoveUnitCommand(GameObject unit, int x, int y) {
        this.unit = unit;
        this.x = this.x + x;
        this.y = this.y + y;
        allCommands.add(this);
    }

    @Override
    public void execute() {
        xBefore = (int) unit.getX();
        yBefore = (int) unit.getY();
        unit.moveTo(x, y);
    }

    @Override
    public void undo() {
        unit.moveTo(xBefore, yBefore);
    }

}
