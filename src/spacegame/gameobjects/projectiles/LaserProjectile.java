package spacegame.gameobjects.projectiles;

import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.controller.Point;
import spacegame.gameobjects.Player;

public class LaserProjectile extends PlayerProjectile {

    private static int BOUND_WIDTH = 10;
    private static int BOUND_HEIGHT = 2;
    private Point direction;

    public LaserProjectile(double x, double y, int speed, Textures tex, Player player, Point direction,
            int damageValue) {
        super(x, y, speed, tex, new Animation(10, tex.laser), BOUND_WIDTH, BOUND_HEIGHT, player, damageValue);
        hitboxXOffset = 0;
        hitboxYOffset = 0;
        this.direction = direction;
    }

    @Override
    public void tick() {
        super.tick();

        double xStep = direction.getX() * speed;
        double yStep = direction.getY() * speed;

        x += xStep;
        y += yStep;

        setX(getX() + xStep);
        setY(getY() + yStep);

    }
}
