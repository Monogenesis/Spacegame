package spacegame.gameobjects.projectiles;

import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.controller.Controller;
import spacegame.gameobjects.Player;
import spacegame.gameobjects.enemies.Enemy;

public class RocketProjectile extends Projectile {

	private int direction;

	public RocketProjectile(double x, double y, Textures tex, Player player, int direction) {
		super(x, y, 5, tex, new Animation(5, tex.bullet), 17, 7, player);
		this.direction = direction;

		hitboxXOffset = -2;
		hitboxYOffset = -2;
	}

	@Override
	public void tick() {
		super.tick();

		if (currentSpeed < maxSpeed) {
			currentSpeed += velocity;
		}
		x += (currentSpeed * direction);

		for (int i = 0; i < Controller.entities.size(); i++) {
			if (Controller.entities.get(i) instanceof Enemy) {
				Enemy tempEnemy = (Enemy) Controller.entities.get(i);
				if (collision(getHitbox(), tempEnemy.getHitbox())) {
					tempEnemy.destroySelf(this);
					destroySelf(this);
				}
			}

		}
	}

}
