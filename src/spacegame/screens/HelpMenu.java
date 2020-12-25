package spacegame.screens;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import spacegame.Game;
import spacegame.controller.Controller;

public class HelpMenu extends Menu {

    public MenuButton mainMenuButton;

    public static Font helpTextFont = new Font("arial", Font.BOLD, 15);
    public final static String menuText = "MENU";
    private BufferedImage background;

    private MenuButton menuButton;

    public HelpMenu(String label, Controller controller, BufferedImage background) {
        super(label, controller, MainMenu.menuLabelFont, MainMenu.labelColor);
        this.background = background;
        menuButtons.add(menuButton = new MenuButton(0, 420, menuText, true));
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);

    }

}