package spacegame.projectiles;

import java.awt.Graphics;
import java.awt.Rectangle;

import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.controller.Controller;
import spacegame.entities.GameObject;
import spacegame.entities.Player;
import spacegame.entities.Enemy;
import spacegame.entities.Entity;

public class Bullet extends Projectile implements Entity {

	private double x;
	private double y;

	private double currentSpeed = 0;
	private double maxSpeed = 10;
	private double velocity = 0.1;

	private static int BOUNDWIDTH = 15;
	private static int BOUNDHEIGHT = 5;

	private Animation anima;
	private Player player;

	public Bullet(double x, double y, Textures tex, Player player) {
		this.x = x;
		this.y = y;
		this.player = player;
		anima = new Animation(5, tex.bullet);
	}

	public Bullet shoot() {
		player.setAmmunitionCount(player.getAmmunitionCount() - 1);
		return this;
	}

	public void tick() {
		if (currentSpeed < maxSpeed) {
			currentSpeed += velocity;
		}
		x += currentSpeed;
		anima.runAnimation();
		for (int i = 0; i < Controller.entities.size(); i++) {
			if (Controller.entities.get(i) instanceof Enemy) {
				Enemy tempEnemy = (Enemy) Controller.entities.get(i);
				if (collision(getBounds(), tempEnemy.getBounds())) {
					tempEnemy.destroySelf(this);
					Controller.entities.remove(this);
				}
			}

		}

	}

	public void render(Graphics g) {

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

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, BOUNDWIDTH, BOUNDHEIGHT);
	}

}
