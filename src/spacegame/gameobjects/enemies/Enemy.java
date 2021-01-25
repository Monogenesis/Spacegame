package spacegame.gameobjects.enemies;

import java.awt.Graphics;

import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.gameobjects.Entity;
import spacegame.gameobjects.GameObject;
import spacegame.gameobjects.Player;
import spacegame.gameobjects.projectiles.RocketProjectile;

public class Enemy extends GameObject {

    public static int enemyCounter;
    protected int hitpoints;

    protected Enemy(double x, double y, int speed, Textures tex, Animation animation, int hitboxWidth, int hitboxHeight,
            int hitpoints) {
        super(x, y, speed, tex, animation, hitboxWidth, hitboxHeight);
        this.hitpoints = hitpoints;
        enemyCounter++;
    }

    public void loseHitpoints(GameObject reason, int damage) {
        this.hitpoints -= damage;
        if (hitpoints <= 0) {
            destroySelf(reason);
        }
    }

    @Override
    public void destroySelf(Entity reason) {
        decrementEnemyCounter();
        if (reason instanceof RocketProjectile) {
            Player.scoreValue += scoreValue;
        }
        super.destroySelf(reason);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        anima.drawAnimation(g, getX(), getY());
    }

    public static void decrementEnemyCounter() {
        enemyCounter--;
    }
}
