package spacegame.screens;

import java.awt.Color;
import java.awt.Graphics;

import spacegame.controller.Controller;
import spacegame.gameobjects.Player;

public class ScoreMenu extends Menu {

    public static String labelText = "SCORE";
    public final static String menuText = "MENU";
    public final static String restartText = "RESTART";

    private MenuButton restartButton;
    private MenuButton menuButton;

    public ScoreMenu(String label, Controller controller) {
        super(label, controller, MainMenu.menuLabelFont, MainMenu.labelColor);
        menuButtons.add(restartButton = new MenuButton(0, 350, restartText, true));
        menuButtons.add(menuButton = new MenuButton(0, 420, menuText, true));
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.setColor(Color.orange);
        g.drawString(String.valueOf(Player.score),
                MainMenu.getTextWorldCenterXPos(MainMenu.menuLabelFont, String.valueOf(Player.score)), 250);
    }

}