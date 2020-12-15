package spacegame.entities;

import spacegame.animation.Animation;
import spacegame.animation.Textures;

public class AmmunitionDrop extends Enemy {

    public AmmunitionDrop(double x, double y, int speed, Textures tex) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        anima = new Animation(7, tex.ammunition);
    }
}
