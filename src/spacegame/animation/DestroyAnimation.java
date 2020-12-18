package spacegame.animation;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import spacegame.controller.Controller;
import spacegame.entities.Entity;

public class DestroyAnimation extends Animation implements Entity {

    private int xOffset, yOffset;
    private double x, y;

    public DestroyAnimation(double x, double y, int speed, BufferedImage[] images, int xOffset, int yOffset) {
        super(speed, images);
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.x = x;
        this.y = y;
    }

    @Override
    public void tick() {
        this.runAnimation();
        if (getCount() == getFrames()) {
            Controller.entities.remove(this);
        }
    }

    @Override
    public void render(Graphics g) {
        this.drawAnimation(g, x, y, xOffset, yOffset);
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