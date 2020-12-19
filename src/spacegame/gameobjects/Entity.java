package spacegame.gameobjects;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface Entity {

	public void tick();

	public void render(Graphics g);

	public Rectangle getBounds();

	public double getX();

	public void setX(double x);

	public double getY();

	public void setY(double y);

}
