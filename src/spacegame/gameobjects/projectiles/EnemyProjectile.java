package spacegame.gameobjects.projectiles;

import java.util.ArrayList;

import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.gameobjects.Entity;
import spacegame.gameobjects.Player;

public class EnemyProjectile extends Projectile {

    public static ArrayList<EnemyProjectile> enemyProjectiles = new ArrayList<>();

    public EnemyProjectile(double x, double y, int speed, Textures tex, Animation animation, int boundWidth,
            int boundHeight, Player player, int damageValue) {
        super(x, y, speed, tex, animation, boundWidth, boundHeight, player, damageValue);
        enemyProjectiles.add(this);
    }

    @Override
    public void destroySelf(Entity reason) {
        super.destroySelf(reason);
        enemyProjectiles.remove(this);
    }

    @Override
    public void tick() {
        super.tick();

        if (collision(getHitbox(), player.getHitbox())) {
            player.loseHealth();
            destroySelf(player);
        }

    }

}
