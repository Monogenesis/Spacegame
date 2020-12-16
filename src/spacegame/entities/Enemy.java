package spacegame.entities;

import spacegame.animation.Animation;
import spacegame.animation.Textures;

public class Enemy extends GameObject {
    private boolean movingRight = true;

    public Enemy(double x, double y, int speed, Textures tex) {
        super(x, y, speed, tex);
        anima = new Animation(4, tex.enemy);
    }

    @Override
    public void tick() {
        if (movingRight) {
            x -= speed;
        } else {
            x += speed;
        }
        if (x < -32) {
            movingRight = false;
        } else if (x > 640) {
            movingRight = true;
        }

        anima.runAnimation();
    }
}
