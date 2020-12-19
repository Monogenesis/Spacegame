package spacegame.entities;

import java.awt.Rectangle;

import spacegame.animation.Animation;
import spacegame.animation.Textures;

public class AmmunitionDrop extends GameObject {
    private int value = 10;

    public AmmunitionDrop(double x, double y, int speed, Textures tex) {
        super(x, y, speed, tex);
        anima = new Animation(7, tex.ammunition);
        hitboxYOffset = 3;
        BOUNDHEIGHT = 20;
        BOUNDWIDTH = 17;
    }

    @Override
    public void destroySelf(Entity reason) {

    }

    public int getAmmunitionValue() {
        System.out.printf("+ %s Ammunition%n", value);
        return this.value;
    }

}
