package spacegame.projectiles;

import java.awt.Rectangle;

public class Projectile {

	private double x, y;

	public static Boolean collision(Rectangle a, Rectangle b) {

		return a.intersects(b);
	}

}
