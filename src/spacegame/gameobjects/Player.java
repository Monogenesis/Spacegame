package spacegame.gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.sound.sampled.SourceDataLine;

import spacegame.animation.Animation;
import spacegame.animation.PlayerTurnAnimation;
import spacegame.animation.Textures;
import spacegame.controller.Controller;
import spacegame.controller.Point;
import spacegame.controller.filehandler.HighscoreLoader;
import spacegame.controller.helper.Score;
import spacegame.gameobjects.enemies.Enemy;
import spacegame.gameobjects.projectiles.LaserProjectile;
import spacegame.gameobjects.projectiles.RocketProjectile;

public class Player extends GameObject {

	public static Player player;

	public Point position = new Point();;

	private double x;
	private double y;

	private int velX = 0;
	private int velY = 0;

	private boolean lookingRight = true;
	private boolean turning = false;

	private static int startXPos = 200;
	private static int startYPos = 200;

	private Textures tex;
	Controller controller;
	private int health = 3;
	private int ammunitionCount = 40;
	public static int scoreValue = 0;
	private Animation leftAnimation;

	public Player(double x, double y, Textures tex) {
		super(x, y, 1, tex, new Animation(4, tex.playerLookRight), 16, 19);
		this.x = x;
		this.y = y;
		this.tex = tex;
		init();
		hitboxXOffset = 3;
		player = this;
		leftAnimation = new Animation(4, tex.playerLookLeft);

	}

	@Override
	public void moveTo(int x, int y) {
		if (lookingRight && x < 0) {
			turn();
		} else if (!lookingRight && x > 0) {
			turn();
		}
		this.x += x;
		this.y += y;
		position.setX(this.x);
		position.setY(this.y);
	}

	public void shootRocket() {
		if (!turning && ammunitionCount > 0) {
			ammunitionCount--;
			controller.addEntity(new RocketProjectile(getX(), getY() + 13, tex, this, lookingRight ? 1 : -1));
		}
	}

	public double clampDouble(double d, double min, double max) {
		if (d > max) {
			return max;
		} else if (d < min) {
			return min;
		}
		return d;
	}

	public void shootLaser(Point direction) {
		if (!turning) {
			System.out.println(direction);
			// direction is player direction
			if (lookingRight && direction.getX() > 0) {
				// Check if direction is in the maximum angle range
				Point tmp = new Point(clampDouble(direction.getX(), 0.95, 1), clampDouble(direction.getY(), -0.2, 0.2));
				controller.addEntity(new LaserProjectile(getX(), getY(), 4, tex, player, tmp));
			} else if (!lookingRight && direction.getX() < 0) {
				// Check if direction is in the maximum angle range
				Point tmp = new Point(clampDouble(direction.getX(), -1, -0.95),
						clampDouble(direction.getY(), -0.2, 0.2));
				controller.addEntity(new LaserProjectile(getX(), getY(), 4, tex, player, tmp));
			}

		}
	}

	public void init() {
		turning = false;
		lookingRight = true;
		x = startXPos;
		position.setX(x);
		y = startYPos;
		position.setY(y);

		ammunitionCount = 40;
		health = 3;
	}

	public Controller getController() {
		return this.controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public Point getCenterPos() {
		return new Point(getX() + hitboxWidth, getY() + hitboxHeight);
	}

	public void tick() {
		x += velX;
		y += velY;
		position.setX(x);
		position.setY(y);
		if (x <= 0)
			x = 0;
		if (x >= 640 - 20)
			x = 640 - 20;
		if (y <= 0)
			y = 0;
		if (y >= 480 - 32)
			y = 480 - 32;

		// TODO Optimization
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
		if (!turning && lookingRight)
			anima.runAnimation();
		else if (!turning)
			leftAnimation.runAnimation();
	}

	public void loseHealth() {
		--health;
		if (health <= 0) {
			resetPlayer();
		}
	}

	private void resetPlayer() {
		new Score(scoreValue);
		HighscoreLoader.saveHighscores();
		init();
		controller.restartlevel();
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		if (!turning) {
			if (lookingRight) {
				anima.drawAnimation(g, getX(), getY());
			} else {
				leftAnimation.drawAnimation(g, getX(), getY());
			}
		}

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

	public int setVelY(int velY) {
		return this.velY = velY;
	}

	public int setVelX(int velX) {
		return this.velX = velX;
	}

	public void moveRight() {
		if (lookingRight) {
			setVelX(5);
		} else {
			turn();
		}
	}

	public void moveLeft() {
		if (!lookingRight) {
			setVelX(-5);
		} else {
			turn();
		}

	}

	public void moveUp() {
		setVelY(-5);
	}

	public void moveDown() {
		setVelY(5);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		position.setX(x);
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		position.setY(y);
		this.y = y;
	}

	public int getAmmunitionCount() {
		return this.ammunitionCount;
	}

	public void setAmmunitionCount(int ammunitionCount) {
		this.ammunitionCount = ammunitionCount;
	}

	public boolean isTurning() {
		return this.turning;
	}

	public boolean getTurning() {
		return this.turning;
	}

	public void turn() {
		if (!turning && lookingRight) {
			Controller.entities.add(new PlayerTurnAnimation(getX(), getY(), 3, tex.playerTurnLeft, 0, 0));
			setTurning(true);
			changeDirection();
		} else if (!turning) {
			Controller.entities.add(new PlayerTurnAnimation(getX(), getY(), 3, tex.playerTurnRight, 0, 0));
			setTurning(true);
			changeDirection();
		}
	}

	public void setTurning(boolean turning) {
		this.turning = turning;

	}

	public boolean isLookingRight() {
		return this.lookingRight;
	}

	public boolean getLookingRight() {
		return this.lookingRight;
	}

	public void changeDirection() {
		this.lookingRight = !lookingRight;
	}

	@Override
	public String toString() {
		return "{" + " x='" + x + "'" + ", y='" + y + "'" + ", velX='" + velX + "'" + ", velY='" + velY + "'" + "}";
	}

}
