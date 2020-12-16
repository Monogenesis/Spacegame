package spacegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.controller.Controller;
import spacegame.projectiles.Projectile;

public class Player implements Entity {

	private double x;
	private double y;

	private double velX = 0;
	private double velY = 0;

	public static int BOUNDWIDTH = 25;
	public static int BOUNDHEIGHT = 17;

	private static int startXPos = 200;
	private static int startYPos = 200;

	private Textures tex;

	private Animation anima;
	Controller controller;
	private int health = 3;
	private int ammunitionCount = 40;
	public static int score = 0;

	public Player(double x, double y, Textures tex) {
		this.x = x;
		this.y = y;
		this.tex = tex;
		init();
		anima = new Animation(4, tex.player);
	}

	private void init() {
		x = startXPos;
		y = startYPos;
		ammunitionCount = 40;
		health = 3;
	}

	public Controller getController() {
		return this.controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public void tick() {

		x += velX;
		y += velY;

		if (x <= 0)
			x = 0;
		if (x >= 640 - 20)
			x = 640 - 20;
		if (y <= 0)
			y = 0;
		if (y >= 480 - 32)
			y = 480 - 32;

		for (int i = 0; i < Controller.e.size(); i++) {
			if (Projectile.collision(getbounds(), Controller.e.get(i).getbounds())) {
				if (Controller.e.get(i) instanceof Enemy) {
					Enemy tempEnemy = (Enemy) Controller.e.get(i);
					tempEnemy.destroySelf(this);
					Controller.e.remove(tempEnemy);
					loseHealth();
				} else if (Controller.e.get(i) instanceof AmmunitionDrop) {
					AmmunitionDrop tmpAmmunitionDrop = (AmmunitionDrop) Controller.e.get(i);
					ammunitionCount += tmpAmmunitionDrop.getAmmunitionValue();
					tmpAmmunitionDrop.destroySelf(this);
					Controller.e.remove(tmpAmmunitionDrop);
				}
			}
		}
		anima.runAnimation();
	}

	public void loseHealth() {
		--health;
		if (health <= 0) {
			resetPlayer();
		}
	}

	private void resetPlayer() {
		init();
		controller.restartlevel();
	}

	public void render(Graphics g) {
		anima.drawAnimation(g, x, y, 0);
		if (health == 3)
			g.drawImage(tex.health3, 20, 20, null);
		else if (health == 2)
			g.drawImage(tex.health2, 20, 20, null);
		else if (health == 1)
			g.drawImage(tex.health1, 20, 20, null);

	}

	public Rectangle getbounds() {
		return new Rectangle((int) x, (int) y, BOUNDWIDTH, BOUNDHEIGHT);
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getAmmunitionCount() {
		return this.ammunitionCount;
	}

	public void setAmmunitionCount(int ammunitionCount) {
		this.ammunitionCount = ammunitionCount;
	}

	@Override
	public String toString() {
		return "{" + " x='" + x + "'" + ", y='" + y + "'" + ", velX='" + velX + "'" + ", velY='" + velY + "'" + "}";
	}

}
