package spacegame.entities;

import spacegame.animation.Animation;
import spacegame.animation.Textures;

public class AmmunitionDrop extends GameObject {
    private int value = 5;

    public AmmunitionDrop(double x, double y, int speed, Textures tex) {
        super(x, y, speed, tex);
        anima = new Animation(7, tex.ammunition);
    }

    public int getAmmunitionValue() {
        System.out.printf("+ %s Ammunition%n", value);
        return this.value;
    }

}
