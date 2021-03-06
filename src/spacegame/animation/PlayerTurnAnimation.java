package spacegame.animation;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import spacegame.controller.Controller;
import spacegame.gameobjects.Entity;
import spacegame.gameobjects.Player;

public class PlayerTurnAnimation extends Animation implements Entity {

    private int xOffset, yOffset;
    private double x, y;

    public PlayerTurnAnimation(double x, double y, int speed, BufferedImage[] images, int xOffset, int yOffset) {
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
            Player.player.setTurning(false);
            // Player.player.changeDirection();
            Controller.entities.remove(this);
        }
    }

    @Override
    public void render(Graphics g) {
        this.drawAnimation(g, Player.player.position.getX(), Player.player.position.getY(), xOffset, yOffset);
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