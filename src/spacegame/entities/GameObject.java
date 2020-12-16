package spacegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.projectiles.Bullet;

public class GameObject implements Entity {

	protected double x, y;

	private static int BOUNDWIDTH = 26;
	private static int BOUNDHEIGHT = 18;

	protected Animation anima;
	protected int scoreValue = 15;
	protected int speed = 2;

	public GameObject(double x, double y, int speed, Textures tex) {
		this.x = x;
		this.y = y;
		this.speed = speed;
	}

	public void tick() {
		x -= speed;
		if (x < -32)
			x = 640;
		anima.runAnimation();
	}

	public void render(Graphics g) {
		anima.drawAnimation(g, x, y);
	}

	public void destroySelf(Entity reason) {
		if (reason instanceof Bullet)
			Player.score += scoreValue;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public Rectangle getbounds() {
		return new Rectangle((int) x, (int) y, BOUNDWIDTH, BOUNDHEIGHT);
	}

	public int getSpeed() {
		return this.speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
