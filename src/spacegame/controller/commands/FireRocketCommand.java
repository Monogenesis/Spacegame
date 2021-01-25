package spacegame.controller.commands;

import spacegame.gameobjects.Player;

public class FireRocketCommand implements Command {
    private Player player;

    public FireRocketCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.shootRocket();
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub

    }

}
