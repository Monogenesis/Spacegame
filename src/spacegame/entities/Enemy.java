package spacegame.entities;

import java.awt.Graphics;
import java.io.ObjectInputStream.GetField;

import spacegame.animation.Animation;
import spacegame.animation.DestroyAnimation;
import spacegame.animation.Textures;
import spacegame.controller.Controller;

public class Enemy extends GameObject {
    private boolean movingFromRight = true;
    private Textures tex;

    public Enemy(double x, double y, int speed, Textures tex) {
        super(x, y, speed, tex);
        this.tex = tex;
        anima = new Animation(4, tex.enemy);
    }

    public void destroySelf(Entity reason) {
        super.destroySelf(reason);
        Controller.entities.add(new DestroyAnimation(x, y, 4, tex.enemyDestroy, 3, 7));
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

    @Override
    public void render(Graphics g) {
        anima.drawAnimation(g, x, y);
    }
}
