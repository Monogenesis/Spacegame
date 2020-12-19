package spacegame.screens;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.image.BufferedImage;
import spacegame.Game;
import spacegame.controller.Controller;
import spacegame.gameobjects.Entity;
import spacegame.gameobjects.Player;

import java.awt.Color;

public class HelpScreen implements Entity {
    private double x, y;

    public MenuButton mainMenuButton;
    private int labelTextPos;
    private String labelText = "HELP";
    private static int BOUNDWIDTH = 15;
    private static int BOUNDHEIGHT = 5;
    public static Font helpTextFont = new Font("arial", Font.BOLD, 15);
    private Controller controller;
    private BufferedImage background;

    public HelpScreen(Controller controller, BufferedImage background) {
        this.controller = controller;
        this.background = background;
        mainMenuButton = new MenuButton(0, 420, "MENU", true);
        labelTextPos = Menu.getTextWorldCenterXPos(Menu.menuLabelFont, labelText);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
        g.setFont(Menu.menuLabelFont);
        g.setColor(Menu.labelColor);
        g.drawString(labelText, labelTextPos, 100);
        g.setFont(helpTextFont);

        mainMenuButton.render(g);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, BOUNDWIDTH, BOUNDHEIGHT);
    }

    @Override
    public double getX() {

        return x;
    }

    @Override
    public void setX(double x) {
        this.x = x;

    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setY(double y) {
        this.y = y;

    }

}