package spacegame.gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;

import spacegame.Game;
import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.controller.Controller;
import spacegame.gameobjects.enemies.Enemy;
import spacegame.gameobjects.projectiles.Bullet;

public class Player extends GameObject {

	public static Player player;

	private double x;
	private double y;

	private double velX = 0;
	private double velY = 0;

	private static int startXPos = 200;
	private static int startYPos = 200;

	private Textures tex;
	Controller controller;
	private int health = 3;
	private int ammunitionCount = 40;
	public static int score = 0;

	public Player(double x, double y, Textures tex) {
		super(x, y, 1, tex, new Animation(4, tex.player), 16, 19);
		this.x = x;
		this.y = y;
		this.tex = tex;
		init();
		hitboxXOffset = 3;
		player = this;
	}

	public void shoot() {
		if (ammunitionCount > 0) {
			ammunitionCount--;
			controller.addEntity(new Bullet(getX(), getY() + 13, tex, this));
		}

	}

	public void init() {
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

		for (int i = 0; i < Controller.entities.size(); i++) {
			Entity entity = Controller.entities.get(i);
			if (entity instanceof GameObject) {
				GameObject gObject = (GameObject) entity;
				if (collision(getHitbox(), gObject.getHitbox())) {
					if (gObject instanceof Enemy) {
						Enemy tempEnemy = (Enemy) gObject;
						tempEnemy.destroySelf(this);
						loseHealth();
					} else if (gObject instanceof AmmunitionDrop) {
						AmmunitionDrop tmpAmmunitionDrop = (AmmunitionDrop) gObject;
						ammunitionCount += tmpAmmunitionDrop.getAmmunitionValue();
						tmpAmmunitionDrop.destroySelf(this);
						Controller.entities.remove(tmpAmmunitionDrop);
					}
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

	@Override
	public void render(Graphics g) {
		super.render(g);

		if (health == 3)
			g.drawImage(tex.health3, 20, 20, null);
		else if (health == 2)
			g.drawImage(tex.health2, 20, 20, null);
		else if (health == 1)
			g.drawImage(tex.health1, 20, 20, null);

	}

	public Rectangle getHitbox() {
		return new Rectangle((int) x + hitboxXOffset, (int) y + hitboxYOffset, hitboxWidth, hitboxHeight);
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
