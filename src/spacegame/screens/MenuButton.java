package spacegame.screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.plaf.ColorUIResource;

import spacegame.Game;
import spacegame.gameobjects.Entity;

public class MenuButton implements Entity {

    private double x, y;
    private int textHeight;
    public boolean enabled;
    private Rectangle2D bounds;
    private Rectangle worldBounds;
    private Color color;

    private String text;

    public MenuButton(int x, int y, String text, boolean enabled) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.enabled = enabled;
        this.bounds = MainMenu.getTextBounds(MainMenu.buttonFont, text);
        this.x = (int) ((Game.WIDTH / 2) - (bounds.getBounds().getWidth() / 2));
        this.textHeight = (int) this.bounds.getBounds().getHeight() - 5;
        worldBounds = new Rectangle((int) this.x, (int) this.y, (int) bounds.getBounds().getWidth(),
                (int) bounds.getBounds().getHeight());
        color = new ColorUIResource(3, 155, 229);
    }

    public MenuButton(int x, int y, String text, boolean enabled, Color color) {
        this(x, y, text, enabled);
        this.color = color;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

        g.setFont(MainMenu.buttonFont);

        if (enabled)
            g.setColor(color != null ? this.color : MainMenu.enabledColor);
        else
            g.setColor(MainMenu.disabledColor);

        g.drawString(text, (int) x, (int) y + textHeight);

    }

    public String getText() {
        return this.text;
    }

    @Override
    public Rectangle getBounds() {
        return worldBounds;
    }

    @Override
    public double getX() {

        return this.x;
    }

    @Override
    public void setX(double x) {
        this.x = x;

    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

}
