package spacegame.gameobjects.projectiles;

import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.controller.Controller;
import spacegame.gameobjects.Player;

public class LightningProjectile extends Projectile {

    public LightningProjectile(double x, double y, int speed, Textures tex, Player player) {
        super(x, y, speed, tex, new Animation(4, tex.enemy2Projectile), 30, 6, player);
        hitboxXOffset = 2;
        hitboxYOffset = 10;
    }

    @Override
    public void tick() {
        super.tick();

        x -= speed;
        setX(getX() - speed);

        if (collision(getHitbox(), player.getHitbox())) {
            player.loseHealth();
            Controller.entities.remove(this);
        }

    }
}
