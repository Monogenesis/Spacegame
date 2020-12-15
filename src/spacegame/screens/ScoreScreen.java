package spacegame.screens;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;

import spacegame.Game;
import spacegame.entities.Entity;
import spacegame.entities.Player;

import java.awt.Color;
import java.awt.Font;

public class ScoreScreen implements Entity {
    private double x, y;

    public Rectangle restartButton = new Rectangle((Game.WIDTH / 2) + 80, 350, 160, 50);
    private Font fnt0 = new Font("arial", Font.BOLD, 50);
    private Font fnt1 = new Font("arial", Font.BOLD, 30);
    private static int BOUNDWIDTH = 15;
    private static int BOUNDHEIGHT = 5;

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g.setFont(fnt0);
        g.setColor(Color.WHITE);
        g.drawString("SCORE " + Player.score, (Game.WIDTH / 2) + 50, 100);

        g.setFont(fnt1);

        g2d.draw(restartButton);
        g.setColor(Color.lightGray);

        g.drawString("RESTART", restartButton.x + 12, restartButton.y + 35);

    }

    @Override
    public Rectangle getbounds() {
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