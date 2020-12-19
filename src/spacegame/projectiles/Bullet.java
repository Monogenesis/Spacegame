package spacegame.projectiles;

import java.awt.Graphics;
import java.awt.Rectangle;

import spacegame.Game;
import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.controller.Controller;
import spacegame.entities.GameObject;
import spacegame.entities.Player;
import spacegame.entities.enemies.Enemy;

public class Bullet extends GameObject {

	private double x;
	private double y;

	private double currentSpeed = 0;
	private double maxSpeed = 10;
	private double velocity = 0.1;

	private Player player;

	public Bullet(double x, double y, Textures tex, Player player) {
		super(x, y, 5, tex);
		this.x = x;
		this.y = y;
		this.player = player;
		anima = new Animation(5, tex.bullet);
		hitboxXOffset = -2;
		hitboxYOffset = -2;
		BOUNDHEIGHT = 7;
		BOUNDWIDTH = 17;
	}

	@Override
	public void tick() {

		if (currentSpeed < maxSpeed) {
			currentSpeed += velocity;
		}
		x += currentSpeed;
		anima.runAnimation();
		for (int i = 0; i < Controller.entities.size(); i++) {
			if (Controller.entities.get(i) instanceof Enemy) {
				Enemy tempEnemy = (Enemy) Controller.entities.get(i);
				if (collision(getHitbox(), tempEnemy.getHitbox())) {
					tempEnemy.destroySelf(this);
					Controller.entities.remove(this);
				}
			}

		}

	}

	@Override
	public void render(Graphics g) {
		if (Game.drawHitboxes) {
			g.drawRect((int) x + hitboxXOffset, (int) y + hitboxYOffset, BOUNDWIDTH, BOUNDHEIGHT);
			// System.out.println("Draw Hitbox for: " + this);
		}
		anima.drawAnimation(g, x, y);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

}
