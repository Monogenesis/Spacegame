package spacegame.gameobjects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import spacegame.Game;
import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.controller.Controller;
import spacegame.controller.Point;

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

	public Point getCenterPos() {
		return new Point(getX() + hitboxWidth, getY() + hitboxHeight);
	}

	public void moveTo(int x, int y) {
		setX(x);
		setY(y);
	}

	public void tick() {
		x -= speed;
		anima.runAnimation();
	}

	public void render(Graphics g) {
		if (Game.drawHitboxes) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.draw(this.getHitbox());
		}

	}

	public void destroySelf(Entity reason) {
		Controller.entities.remove(this);
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
