package spacegame.gameobjects.projectiles;

import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.gameobjects.Player;

public class RocketProjectile extends PlayerProjectile {

	private int direction;

	public RocketProjectile(double x, double y, Textures tex, Player player, int direction, int damageValue) {
		super(x, y, 5, tex, new Animation(5, tex.bullet), 17, 7, player, damageValue);
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

	}

}
