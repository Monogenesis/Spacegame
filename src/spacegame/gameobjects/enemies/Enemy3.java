package spacegame.gameobjects.enemies;

import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.controller.Controller;
import spacegame.gameobjects.Player;
import spacegame.gameobjects.projectiles.OscillatingProjectile;

public class Enemy3 extends Enemy {

    private boolean shotReady;
    public static int DEFAULT_HITPOINTS = 4;

    public Enemy3(double x, double y, int speed, Textures tex) {
        super(x, y, speed, tex, new Animation(2, tex.enemy3), 32, 17, DEFAULT_HITPOINTS);
        hitboxXOffset = 0;
        hitboxYOffset = 7;
    }

    @Override
    public void tick() {
        if (shotReady && (Controller.time) % 2 == 0) {
            if (Player.player.getX() <= getX()) {
                Controller.entities.add(new OscillatingProjectile(x, y, 2, tex, Player.player, true, 1));
            } else {
                Controller.entities.add(new OscillatingProjectile(x + 32, y, 2, tex, Player.player, false, 1));
            }
            shotReady = false;
        } else if ((Controller.time) % 2 != 0) {
            shotReady = true;
        }
        x -= speed;
        if (x < -40)
            x = 640;
        anima.runAnimation();

    }
}
