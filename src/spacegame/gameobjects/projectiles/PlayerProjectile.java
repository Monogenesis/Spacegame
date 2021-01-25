package spacegame.gameobjects.projectiles;

import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.controller.Controller;
import spacegame.gameobjects.Player;
import spacegame.gameobjects.enemies.Enemy;

public class PlayerProjectile extends Projectile {

    public PlayerProjectile(double x, double y, int speed, Textures tex, Animation animation, int boundWidth,
            int boundHeight, Player player, int damageValue) {
        super(x, y, speed, tex, animation, boundWidth, boundHeight, player, damageValue);
    }

    @Override
    public void tick() {
        super.tick();

        for (int i = 0; i < Controller.entities.size(); i++) {
            if (Controller.entities.get(i) instanceof Enemy) {
                Enemy tempEnemy = (Enemy) Controller.entities.get(i);
                if (collision(getHitbox(), tempEnemy.getHitbox())) {
                    tempEnemy.loseHitpoints(this, damageValue);
                    destroySelf(this);
                }
            }

        }
    }
}