package spacegame.gameobjects.projectiles;

import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.controller.Controller;
import spacegame.gameobjects.Player;

public class OscillatingProjectile extends EnemyProjectile {
    private boolean movingLeft;

    public OscillatingProjectile(double x, double y, int speed, Textures tex, Player player, boolean movingLeft,
            int damageValue) {
        super(x, y, speed, tex, new Animation(2, tex.enemy3Projectile), 7, 21, player, damageValue);
        hitboxXOffset = 0;
        hitboxYOffset = 6;
        this.movingLeft = movingLeft;
    }

    @Override
    public void tick() {
        super.tick();
        if (movingLeft) {
            x -= speed;
            setX(getX() - speed);
        } else {
            x += speed;
            setX(getX() + speed);
        }

    }
}
