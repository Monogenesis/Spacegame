package spacegame.entities.enemies;

import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.entities.GameObject;

public class Enemy extends GameObject {

    protected Enemy(double x, double y, int speed, Textures tex, Animation animation, int hitboxWidth,
            int hitboxHeight) {
        super(x, y, speed, tex, animation, hitboxWidth, hitboxHeight);

    }

}
