package spacegame.gameobjects.enemies;

import spacegame.animation.Animation;
import spacegame.animation.DestroyAnimation;
import spacegame.animation.Textures;
import spacegame.controller.Controller;
import spacegame.gameobjects.Entity;
import spacegame.gameobjects.Player;
import spacegame.gameobjects.projectiles.LightningProjectile;

public class Enemy2 extends Enemy {

    private double sinusCounter;
    private boolean shotReady;
    public static int DEFAULT_HITPOINTS = 3;

    public Enemy2(double x, double y, int speed, Textures tex) {
        super(x, y, speed, tex, new Animation(5, tex.enemy2), 30, 24, DEFAULT_HITPOINTS);
        hitboxXOffset = 3;
        hitboxYOffset = 5;
    }

    @Override
    public void tick() {
        if (shotReady && (Controller.time) % 2 == 0) {
            Controller.entities.add(new LightningProjectile(x, y, 2, tex, Player.player, 1));
            shotReady = false;
        } else if ((Controller.time) % 2 != 0) {
            shotReady = true;
        }
        double heightModifier = 2 * Math.sin(sinusCounter);
        y += heightModifier;
        sinusCounter += 0.1;
        x -= speed;
        anima.runAnimation();
    }

    public void destroySelf(Entity reason) {

        Controller.entities.add(new DestroyAnimation(x, y, 5, tex.enemy2Destroy, 0, 0));

        super.destroySelf(reason);
    }
}
