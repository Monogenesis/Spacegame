package spacegame.entities.enemies;

import java.awt.Rectangle;

import spacegame.animation.Animation;
import spacegame.animation.DestroyAnimation;
import spacegame.animation.Textures;
import spacegame.controller.Controller;
import spacegame.entities.Entity;

public class Enemy2 extends Enemy {

    private double sinusCounter;

    public Enemy2(double x, double y, int speed, Textures tex) {
        super(x, y, speed, tex);
        anima = new Animation(5, tex.enemy2);

        hitboxXOffset = 3;
        hitboxYOffset = 5;
        BOUNDHEIGHT = 24;
        BOUNDWIDTH = 30;
    }

    @Override
    public void tick() {
        // y += 1 * Math.sin(sinusCounter);
        // sinusCounter += 0.1;
        x -= speed;
        anima.runAnimation();
    }

    public void destroySelf(Entity reason) {
        super.destroySelf(reason);
        Controller.entities.add(new DestroyAnimation(x, y, 6, tex.enemy2Destroy, 0, 0));
        Controller.entities.remove(this);
    }
}
