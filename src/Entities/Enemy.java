package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy implements Entity {

	private double x, y;

	private static int BOUNDWIDTH = 26;
	private static int BOUNDHEIGHT = 18;

	private Textures tex;

	private Animation anima;
	private int scoreValue = 15;
	private int speed = 2;

	public Enemy(double x, double y, Textures tex) {
		this.x = x;
		this.y = y;
		this.tex = tex;

		anima = new Animation(4, tex.enemy[0], tex.enemy[1], tex.enemy[2]);
	}

	public void tick() {

		x -= speed;
		if (x < -32)
			x = 640;

		anima.runAnimation();

	}

	public void render(Graphics g) {
		anima.drawAnimation(g, x, y, 0);

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
