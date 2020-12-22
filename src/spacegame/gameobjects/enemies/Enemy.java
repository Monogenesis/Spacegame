package spacegame.gameobjects.enemies;

import java.awt.Graphics;

import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.gameobjects.GameObject;

public class Enemy extends GameObject {

    protected Enemy(double x, double y, int speed, Textures tex, Animation animation, int hitboxWidth,
            int hitboxHeight) {
        super(x, y, speed, tex, animation, hitboxWidth, hitboxHeight);

    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        anima.drawAnimation(g, getX(), getY());
    }
}
