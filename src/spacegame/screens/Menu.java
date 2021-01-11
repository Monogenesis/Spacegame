package spacegame.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import spacegame.Game;
import spacegame.controller.Controller;
import spacegame.gameobjects.Entity;

public abstract class Menu implements Entity {

    protected ArrayList<MenuButton> menuButtons;
    protected Controller controller;
    protected String label;
    protected int labelTextPos;
    protected Color labelColor;
    protected Font font;
    protected MenuButton selectedButton;

    public Menu(String label, Controller controller, Font font, Color labelColor) {
        this.label = label;
        this.controller = controller;
        this.controller = controller;
        this.font = font;
        this.labelColor = labelColor;
        menuButtons = new ArrayList<>();

        labelTextPos = Menu.getTextWorldCenterXPos(font, label);
    }

    public static int getTextWorldCenterXPos(Font font, String text) {
        return (int) ((Game.WIDTH / 2) - (getTextBounds(font, text).getBounds().getWidth() / 2));
    }

    public static Rectangle2D getTextBounds(Font font, String text) {
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        return font.getStringBounds(text, frc);
    }

    @Override
    public void tick() {
        int mouseX = (int) (MouseInfo.getPointerInfo().getLocation().getX() - Game.frame.getLocationOnScreen().getX());
        int mouseY = (int) (MouseInfo.getPointerInfo().getLocation().getY() - Game.frame.getLocationOnScreen().getY());
        Point pos = new Point(mouseX, mouseY);
        selectedButton = getClickedButton(pos);
    }

    @Override
    public void render(Graphics g) {
        g.setFont(font);
        g.setColor(labelColor);
        g.drawString(label, labelTextPos, 100);
        for (MenuButton button : menuButtons) {
            button.render(g);
        }
        if (selectedButton != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.draw(selectedButton.getBounds());
        }

    }

    public MenuButton getClickedButton(Point mousePos) {
        for (MenuButton button : menuButtons) {
            if (button.getBounds().contains(mousePos)) {
                return button;
            }
        }
        return null;
    }

    @Override
    public Rectangle getBounds() {

        return null;
    }

    @Override
    public double getX() {

        return 0;
    }

    @Override
    public void setX(double x) {

    }

    @Override
    public double getY() {

        return 0;
    }

    @Override
    public void setY(double y) {

    }

}
