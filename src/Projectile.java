import java.awt.Graphics;
import java.awt.Rectangle;

public class Projectile {

	private double x, y;

	public Boolean collision(Rectangle a, Rectangle b) {

		return a.intersects(b);
	}

}
