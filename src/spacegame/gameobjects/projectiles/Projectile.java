package spacegame.gameobjects.projectiles;

import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.gameobjects.GameObject;
import spacegame.gameobjects.Player;

public class Projectile extends GameObject {

    protected double currentSpeed = 0;
    protected double maxSpeed = 10;
    protected double velocity = 0.1;

    protected Player player;

    public Projectile(double x, double y, int speed, Textures tex, Animation animation, int boundWidth, int boundHeight,
            Player player) {
        super(x, y, speed, tex, animation, boundWidth, boundHeight);
        this.player = player;
    }

    @Override
    public void tick() {
        anima.runAnimation();
        x += speed;

    }

}
