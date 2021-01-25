package spacegame.gameobjects.projectiles;

import java.lang.Enum.EnumDesc;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import spacegame.animation.Animation;
import spacegame.animation.Textures;
import spacegame.controller.Point;
import spacegame.gameobjects.Player;
import spacegame.gameobjects.enemies.Enemy;

public class HomingMissileProjectile extends PlayerProjectile {

	private int direction;

	public HomingMissileProjectile(double x, double y, Textures tex, Player player, int direction, int damageValue) {
		super(x, y, 5, tex, new Animation(5, tex.bullet), 17, 7, player, damageValue);
		this.direction = direction;

		hitboxXOffset = -2;
		hitboxYOffset = -2;
	}

	private Point getDirectionToClosestEnemy() {
		if (Enemy.allEnemeies.size() == 0) {
			return new Point(0, 0);
		}
		Map<Enemy, Double> enemyDistance = new HashMap<>();
		for (Enemy enemy : Enemy.allEnemeies) {
			double distanceX = enemy.getCenterPos().getX() - getX();
			double distanceY = enemy.getCenterPos().getY() - getY();
			double distance = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
			enemyDistance.put(enemy, distance);
		}
		double minValue = Collections.min(enemyDistance.values());
		Enemy minDistEnemy = null;
		for (Entry<Enemy, Double> entry : enemyDistance.entrySet()) {
			if (entry.getValue() == minValue) {
				minDistEnemy = entry.getKey();
			}
		}

		// Enemy maxDistEnemy =
		// Optional<Entry<Enemy, Double>> maxEntry = enemyDistance.entrySet().stream()
		// .max((Entry<Enemy, Double> e1, Entry<Enemy, Double> e2) ->
		// e1.getValue().compareTo(e2.getValue()));
		// Enemy maxDistEnemy = maxEntry.get().getKey();
		Point direction = calculateDirection(
				new Point(minDistEnemy.getCenterPos().getX(), minDistEnemy.getCenterPos().getY()),
				new Point(getX(), getY()));

		return new Point(direction.getX(), direction.getY());
	}

	private Point calculateDirection(Point a, Point b) {
		double distanceX = a.getX() - b.getX();
		double distanceY = a.getY() - b.getY();
		double angle = Math.atan(distanceY / (distanceX));
		double angleDregree = Math.toDegrees(angle);

		double x = Math.cos(Math.toRadians(angleDregree));
		double y = Math.sin(Math.toRadians(angleDregree));

		// 1. Quadrant
		if (distanceX >= 0 && distanceY < 0) {
			// System.out.println("1 Q");

		}
		// 2. Quadrant
		else if (distanceX < 0 && distanceY < 0) {
			// System.out.println("2 Q");
			x = Math.abs(x) * (-1);
			y = Math.abs(y) * (-1);

		}
		// 3. Quadrant
		else if (distanceX < 0 && distanceY >= 0) {
			// System.out.println("3 Q");
			x = Math.abs(x) * (-1);
			y = Math.abs(y);

		}
		// 4. Quadrant
		else if (distanceX >= 0 && distanceY >= 0) {
			// System.out.println("4 Q");

		}

		return new Point(x, y);

	}

	@Override
	public void tick() {
		super.tick();
		if (currentSpeed < maxSpeed) {
			currentSpeed += velocity;
		}
		// x += (currentSpeed * direction);
		Point targetDirection = getDirectionToClosestEnemy();
		int xStep = (int) (currentSpeed * targetDirection.getX());
		int yStep = (int) (currentSpeed * targetDirection.getY());
		// setX(getX() + xStep);
		// setY(getY() + yStep);
		x += xStep;
		y += yStep;

	}

}
