package spacegame.entities;

import java.awt.Graphics;
import spacegame.animation.Animation;
import spacegame.animation.Textures;

public class Enemy extends GameObject {
    private boolean movingFromRight = true;

    public Enemy(double x, double y, int speed, Textures tex) {
        super(x, y, speed, tex);
        anima = new Animation(4, tex.enemy);
    }

    @Override
    public void tick() {
        if (movingFromRight) {
            x -= speed;
        } else {
            x += speed;
        }
        if (x < -32) {
            movingFromRight = false;
        } else if (x > 640) {
            movingFromRight = true;
        }

        anima.runAnimation();
    }

    @Override
    public void render(Graphics g) {
        anima.drawAnimation(g, x, y, 0);
    }
}
