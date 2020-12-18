package spacegame.screens;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;

import spacegame.Game;
import spacegame.controller.Controller;
import spacegame.entities.Entity;
import spacegame.entities.Player;

import java.awt.Color;

public class ScoreScreen implements Entity {
    private double x, y;

    public MenuButton restartButton;
    public MenuButton mainMenuButton;
    private int labelTextPos;
    private String labelText = "SCORE";
    private static int BOUNDWIDTH = 15;
    private static int BOUNDHEIGHT = 5;

    private Controller controller;

    public ScoreScreen(Controller controller) {
        this.controller = controller;
        restartButton = new MenuButton(0, 350, "RESTART", true);
        mainMenuButton = new MenuButton(0, 420, "MENU", true);
        labelTextPos = Menu.getTextWorldCenterXPos(Menu.menuLabelFont, labelText);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setFont(Menu.menuLabelFont);
        g.setColor(Color.WHITE);
        g.drawString(labelText, labelTextPos, 100);
        g.setColor(Color.orange);
        g.drawString(String.valueOf(Player.score),
                Menu.getTextWorldCenterXPos(Menu.menuLabelFont, String.valueOf(Player.score)), 250);
        restartButton.render(g);
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