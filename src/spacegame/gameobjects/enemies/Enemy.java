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

    protected Enemy(double x, double y, int speed, Textures tex, Animation animation, int hitboxWidth,
            int hitboxHeight) {
        super(x, y, speed, tex, animation, hitboxWidth, hitboxHeight);
        enemyCounter++;
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
