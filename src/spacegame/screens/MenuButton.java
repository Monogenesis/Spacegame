package spacegame.screens;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import spacegame.Game;
import spacegame.entities.Entity;

public class MenuButton implements Entity {

    private double x, y;
    private int textHeight;
    public boolean enabled;
    private Rectangle2D bounds;
    private Rectangle worldBounds;

    private final String text;

    public MenuButton(int x, int y, String text, boolean enabled) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.enabled = enabled;
        this.bounds = Menu.getTextBounds(Menu.buttonFont, text);
        this.x = (int) ((Game.WIDTH / 2) - (bounds.getBounds().getWidth() / 2));
        this.textHeight = (int) this.bounds.getBounds().getHeight() - 5;
        worldBounds = new Rectangle((int) this.x, (int) this.y, (int) bounds.getBounds().getWidth(),
                (int) bounds.getBounds().getHeight());
    }

    @Override
    public void tick() {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setFont(Menu.buttonFont);
        if (enabled)
            g.setColor(Menu.enabledColor);
        else
            g.setColor(Menu.disabledColor);
        g2d.draw(worldBounds);
        g.drawString(text, (int) x, (int) y + textHeight);

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
