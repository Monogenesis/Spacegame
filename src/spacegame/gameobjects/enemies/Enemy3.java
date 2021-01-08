package spacegame.gameobjects.enemies;

import spacegame.animation.Animation;
import spacegame.animation.Textures;

public class Enemy3 extends Enemy {

    private boolean shotReady;

    public Enemy3(double x, double y, int speed, Textures tex) {
        super(x, y, speed, tex, new Animation(2, tex.enemy3), 32, 17);
        hitboxXOffset = 0;
        hitboxYOffset = 7;
    }

    @Override
    public void tick() {
        x -= speed;
        if (x < -40)
            x = 640;
        anima.runAnimation();
    }
}
