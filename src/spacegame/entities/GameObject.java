package spacegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import spacegame.Game;
import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.projectiles.Bullet;

public class GameObject implements Entity {

	protected double x, y;
	protected int hitboxXOffset = 0;
	protected int hitboxYOffset = 0;
	public Textures tex;
	protected Rectangle hitbox;
	protected int hitboxWidth = 5;
	protected int hitboxHeight = 5;

	protected Animation anima;
	protected int scoreValue = 15;
	protected int speed = 2;

	public GameObject(double x, double y, int speed, Textures tex, Animation animation, int boundWidth,
			int boundHeight) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.tex = tex;
		this.hitboxWidth = boundWidth;
		this.hitboxHeight = boundHeight;
		anima = animation;
	}

	public void tick() {
		x -= speed;
		if (x < -32)
			x = 640;
		anima.runAnimation();
	}

	public void render(Graphics g) {
		if (Game.drawHitboxes) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.draw(this.getHitbox());
			// g.drawRect(getHitbox().x, getHitbox().y, getHitbox().width,
			// getHitbox().height);
		}
		anima.drawAnimation(g, getX(), getY());
	}

	public void destroySelf(Entity reason) {
		if (reason instanceof Bullet)
			Player.score += scoreValue;

		System.out.println("Destoyed: " + this);
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

	public Rectangle getHitbox() {
		return new Rectangle((int) getX() + hitboxXOffset, (int) getY() + hitboxYOffset, hitboxWidth, hitboxHeight);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, hitboxWidth, hitboxHeight);
	}

	public int getSpeed() {
		return this.speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public static Boolean collision(Rectangle a, Rectangle b) {

		return a.intersects(b);
	}
}
