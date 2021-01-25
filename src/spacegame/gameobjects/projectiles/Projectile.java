package spacegame.gameobjects.projectiles;

import java.awt.Graphics;
import java.util.ArrayList;

import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.gameobjects.Entity;
import spacegame.gameobjects.GameObject;
import spacegame.gameobjects.Player;

public class Projectile extends GameObject {

    public static ArrayList<Projectile> activeProjectiles = new ArrayList<>();
    protected double currentSpeed = 0;
    protected double maxSpeed = 10;
    protected double velocity = 0.1;
    protected int damageValue;
    protected Player player;

    public enum TYPE {
        ROCKET, LASER
    }

    public Projectile(double x, double y, int speed, Textures tex, Animation animation, int boundWidth, int boundHeight,
            Player player, int damageValue) {
        super(x, y, speed, tex, animation, boundWidth, boundHeight);
        activeProjectiles.add(this);
        this.player = player;
        this.damageValue = damageValue;
    }

    @Override
    public void render(Graphics g) {

        super.render(g);
        anima.drawAnimation(g, getX(), getY());
    }

    @Override
    public void tick() {
        anima.runAnimation();

    }

    @Override
    public void destroySelf(Entity reason) {
        super.destroySelf(reason);
        activeProjectiles.remove(this);
    }
}
