package spacegame.entities;

import java.awt.Graphics;
import java.io.ObjectInputStream.GetField;

import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.controller.Controller;

public class Enemy extends GameObject {
    private boolean movingFromRight = true;
    protected Animation animaDestroy;
    private boolean destroyed;

    public Enemy(double x, double y, int speed, Textures tex) {
        super(x, y, speed, tex);
        anima = new Animation(4, tex.enemy);
        animaDestroy = new Animation(3, tex.enemyDestroy);
    }

    public void destroySelf(Entity reason) {
        super.destroySelf(reason);
        destroyed = true;

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
        if (!destroyed) {
            anima.runAnimation();
        } else {
            animaDestroy.runAnimation();
            if (animaDestroy.getCount() == animaDestroy.getFrames()) {
                Controller.e.remove(this);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (!destroyed) {
            anima.drawAnimation(g, x, y);
        } else {
            animaDestroy.drawAnimation(g, x, y, 3, 7);
        }
    }
}
