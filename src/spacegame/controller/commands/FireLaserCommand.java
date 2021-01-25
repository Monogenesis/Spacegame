package spacegame.controller.commands;

import spacegame.controller.Point;
import spacegame.gameobjects.Player;

public class FireLaserCommand implements Command {

    private Player player;
    private Point direction;

    public FireLaserCommand(Player player, Point direction) {
        this.player = player;
        this.direction = direction;

    }

    @Override
    public void execute() {
        player.shootLaser(direction);

    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
