package spacegame.entities;

import spacegame.animation.Animation;
import spacegame.animation.Textures;

public class Enemy extends GameObject {
    public Enemy(double x, double y, int speed, Textures tex) {
        super(x, y, speed, tex);
        anima = new Animation(4, tex.enemy);
    }
}
