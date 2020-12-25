package spacegame.gameobjects.enemies;

import spacegame.animation.Animation;
import spacegame.animation.DestroyAnimation;
import spacegame.animation.Textures;
import spacegame.controller.Controller;
import spacegame.gameobjects.Entity;

public class Enemy1 extends Enemy {
    private boolean movingFromRight = true;

    public Enemy1(double x, double y, int speed, Textures tex) {
        super(x, y, speed, tex, new Animation(4, tex.enemy1), 26, 17);

    }

    public void destroySelf(Entity reason) {

        Controller.entities.add(new DestroyAnimation(x, y, 4, tex.enemy1Destroy, 3, 7));
        super.destroySelf(reason);
    }

    @Override
    public void tick() {
        if (movingFromRight) {
            x -= speed;
        } else {
            speed = 4;
            x += speed;
        }
        if (x < -32) {
            movingFromRight = false;
        } else if (x > 640) {
            movingFromRight = true;
        }
        anima.runAnimation();
    }

}
