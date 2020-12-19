package spacegame.entities.enemies;

import spacegame.animation.Animation;
import spacegame.animation.DestroyAnimation;
import spacegame.animation.Textures;
import spacegame.controller.Controller;
import spacegame.entities.Entity;

public class Enemy1 extends Enemy {
    private boolean movingFromRight = true;

    public Enemy1(double x, double y, int speed, Textures tex) {
        super(x, y, speed, tex, new Animation(4, tex.enemy1), 26, 17);

    }

    public void destroySelf(Entity reason) {
        if (reason == null) {
            System.out.println("Th REASON WAS NULL");
        }
        super.destroySelf(reason);
        Controller.entities.add(new DestroyAnimation(x, y, 4, tex.enemy1Destroy, 3, 7));
        Controller.entities.remove(this);
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

}
